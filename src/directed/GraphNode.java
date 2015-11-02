package directed;

import java.util.LinkedList;
import java.util.List;

/**
 * Graph Node class.
 * 
 * @author Shashank Shanbhag
 *
 */
public class GraphNode {
  final int label;
  private final List<GraphNode> children;

  /**
   * Creates a {@link GraphNode} object.
   * 
   * @param label an integer label for the node
   */
  public GraphNode(int label) {
    this.label = label;
    this.children = new LinkedList<GraphNode>();
  }

  /**
   * @return children GraphNode objects
   */
  public List<GraphNode> getChildren() {
    return children;
  }

  /**
   * Add an edge from this {@link GraphNode} object to child.
   * 
   * @param child a {@link GraphNode} object
   */
  public void addEdge(GraphNode child) {
    this.children.add(child);
  }
}
