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
  private int order;
  private int leftNodeSize;
  private int rightNodeSize;
  private int middleNodePos;

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

    //  For splitting the node.
    leftNodeSize = (order - 1) / 2;
    rightNodeSize = (order - 1) - leftNodeSize;
    middleNodePos = leftNodeSize;
  }

  public BTreeNode getNewNode() {
    return new BTreeNode(this);
  }

  public int getOrder() {
    return order;
  }

  public int getLeftNodeSize() {
    return leftNodeSize;
  }

  public int getRightNodeSize() {
    return rightNodeSize;
  }

  public int getMiddleNodePos() {
    return middleNodePos;
  }
}
