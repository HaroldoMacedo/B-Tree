package algorithm.btree;

/**
 * Class that creates BTreeNodes.
 * 
 * All BTreeNodes created by this factory has the same order. The order value is
 * kept in the factory object for the nodes to use.
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

  public static BTreeNodeFactory getNodeFactory(int order) {
    return new BTreeNodeFactory(order);
  }

  private BTreeNodeFactory(int order) {
    this.order = order;

    leftNodeSize = (order - 1) / 2;
    rightNodeSize = (order - 1) - leftNodeSize;
    middleNodePos = leftNodeSize;
  }

  public BTreeNode getNode() {
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
