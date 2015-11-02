package directed;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link GraphUtilities}.
 * 
 * @author Shashank Shanbhag
 *
 */
public class GraphUtilitiesTest {

  GraphNode[] nodes;

  @Before
  public void setUp() {
    nodes = new GraphNode[6];
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
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullTopologicalSort() throws IllegalArgumentException {

    GraphNode nullGraphNode = null;
    Stack<GraphNode> stack = null;
    GraphUtilities.topologicalSort(nullGraphNode, stack);
  }

  @Test
  public void testTopologicalSort() {

    Stack<GraphNode> stack = new Stack<>();
    Stack<GraphNode> expected = new Stack<>();
    expected.push(nodes[5]);
    expected.push(nodes[4]);
    expected.push(nodes[3]);
    expected.push(nodes[2]);
    expected.push(nodes[1]);
    expected.push(nodes[0]);

    GraphUtilities.topologicalSort(nodes[0], stack);

    assertEquals(stack.size(), 6);
    while (!stack.isEmpty()) {
      assertEquals(stack.pop().label, expected.pop().label);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMaximumPathLength() throws IllegalArgumentException {

    GraphNode nullGraphNode = null;
    GraphUtilities.maximumPathLength(nullGraphNode);
  }

  @Test
  public void testMaximumPathLength() {
    Map<Integer, List<GraphNode>> longestPath;
    longestPath = GraphUtilities.maximumPathLength(nodes[0]);

    assertEquals(longestPath.size(), 1);
    List<GraphNode> path = null;
    for (Integer maxPathLength : longestPath.keySet()) {
      assertEquals(maxPathLength.intValue(), 5);
      path = longestPath.get(maxPathLength);
    }
    assertEquals(path.size(), 6);
    int nodeLabel = 5;
    for (GraphNode n : path) {
      assertEquals(n.label, nodeLabel);
      nodeLabel--;
    }
  }
}
