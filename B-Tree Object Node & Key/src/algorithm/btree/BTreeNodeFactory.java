package algorithm.btree;

import javax.management.RuntimeErrorException;

/**
 * Class that creates BTreeNodes.
 * 
 * All BTreeNodes created by this factory has the same order.
 * 
 * The order value is kept in the factory object. The nodes use it.
 * 
 * @author Haroldo Macedo
 *
 */
class BTreeNodeFactory {
  // Definition for all nodes created from this factory.
  // To avoid storing this same values in each node, saving space.
  final int order;
  final int leftNodeSize;
  final int rightNodeSize;
  final int middleNodePos;

  /**
   * Return a Node factory that creates nodes with the order size 'order'.
   * 
   * @param order
   * @return
   */
  public BTreeNodeFactory(int order) {
    if (order < 3)
      throw new RuntimeErrorException(new Error("Order must be bigger or equal to 3!"));

    this.order = order;

    // For splitting the node.
    leftNodeSize = (order - 1) / 2;
    rightNodeSize = (order - 1) - leftNodeSize;
    middleNodePos = leftNodeSize;
  }

  /**
   * 
   * @return
   */
  public BTreeNode getNewNode() {
    return new BTreeNode(this);
  }
}
