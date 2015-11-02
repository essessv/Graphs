package directed;

import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    GraphNode[] nodes = new GraphNode[6];

    for (int i = 0; i < 6; i++) {
      GraphNode node = new GraphNode(i);
      nodes[i] = node;
    }
    nodes[0].addEdge(nodes[1]);
    nodes[0].addEdge(nodes[2]);
    nodes[1].addEdge(nodes[3]);
    nodes[1].addEdge(nodes[2]);
    nodes[2].addEdge(nodes[4]);
    nodes[2].addEdge(nodes[5]);
    nodes[2].addEdge(nodes[3]);
    nodes[3].addEdge(nodes[5]);
    nodes[3].addEdge(nodes[4]);
    nodes[4].addEdge(nodes[5]);

    Map<Integer, List<GraphNode>> longestPath;
    int sourceNode = 0;
    longestPath = GraphUtilities.maximumPathLength(nodes[sourceNode]);

    for (Integer maxDistance : longestPath.keySet()) {
      System.out.format("%d: [", maxDistance);

      List<GraphNode> path = longestPath.get(maxDistance);
      for (int i=path.size()-1; i > 0; i--) {
        System.out.format("(%d,%d)", path.get(i).label, path.get(i-1).label);
      }

      System.out.println("]");
    }
  }
}
