package directed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Graph Utilities. Implements:
 * <ul>
 * <li> {@link topologicalSort} - returns a topologically sorted list of {@link GraphNode} objects.
 * <li> {@link maximumPathLength} - returns the maximum path length along with the path from root to
 * farthest node.
 * </ul>
 * 
 * @author Shashank Shanbhag
 */
public class GraphUtilities {

  /*
   * Implementation of Topological graph sorting.
   * 
   * @param a node {@link GraphNode}
   * 
   * @param a {@link Stack} that is updated with the topologically sorted graph nodes
   */
  private static void topoSort(GraphNode node, List<GraphNode> visited, Stack<GraphNode> stack) {
    visited.add(node);
    List<GraphNode> adjacentNodes = node.getChildren();
    for (GraphNode n : adjacentNodes) {
      if (!visited.contains(n)) {
        topoSort(n, visited, stack);
      }
    }
    stack.push(node);
  }

  /**
   * Given the root of a directed acyclic graph, return a topological sorting of the graph.
   * 
   * @param root {@link GraphNode} that is the root of the graph
   * @param stack a {@link Stack} that is updated with the topologically sorted graph nodes
   */
  public static void topologicalSort(GraphNode root, Stack<GraphNode> stack) {

    if (root == null) {
      throw new IllegalArgumentException("root cannot be null.");
    }

    List<GraphNode> visited = new ArrayList<>();
    topoSort(root, visited, stack);
  }

  /**
   * Computes the path from the root to node that is at a maximum distance from the root. Assumes a
   * Directed Acyclic Graph. The behavior for an "Undirected" or "Cyclic" graph is undefined.
   * 
   * @param root a {@link GraphNode} that forms the root of the graph
   * 
   * @return a {@link Map} of longest path length, and the list of nodes that form the longest path
   *         from the root
   */
  public static Map<Integer, List<GraphNode>> maximumPathLength(GraphNode root) {

    if (root == null) {
      throw new IllegalArgumentException("root cannot be null.");
    }

    Map<GraphNode, GraphNode> pathMap = new HashMap<>();
    Map<GraphNode, Integer> distance = new HashMap<>();
    Stack<GraphNode> stack = new Stack<>();

    topologicalSort(root, stack);

    /*
     * Initialize all distances to MIN_VALUE.
     */
    for (GraphNode node : stack) {
      distance.put(node, Integer.MIN_VALUE);
    }
    distance.put(root, 0);

    while (!stack.isEmpty()) {
      GraphNode top = stack.pop();
      List<GraphNode> adjacentNodes = top.getChildren();
      for (GraphNode node : adjacentNodes) {
        if (distance.get(node) <= (distance.get(top) + 1)) {
          pathMap.put(node, top);
          distance.put(node, distance.get(top) + 1);
        }
      }
    }

    /*
     * Get the {@GraphNode} object that is at a maximum distance from {@link GraphNode} root.
     */
    GraphNode nodeWithMaxDistance =
        Collections.max(distance.entrySet(), (e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1)
            .getKey();

    /*
     * Create the final list of nodes that form the path from root to the farthest node in the
     * graph.
     */
    Map<Integer, List<GraphNode>> maxDistanceAndPath = new HashMap<>();
    List<GraphNode> resultPath = new ArrayList<>();
    GraphNode startNode = nodeWithMaxDistance;
    while (pathMap.containsKey(startNode)) {
      resultPath.add(startNode);
      startNode = pathMap.get(startNode);
    }
    resultPath.add(startNode);
    maxDistanceAndPath.put(distance.get(nodeWithMaxDistance), resultPath);
    return maxDistanceAndPath;
  }
}
