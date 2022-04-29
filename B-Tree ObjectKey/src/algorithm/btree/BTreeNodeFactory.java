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
  private BTreeKeyFactory keyFactory;
  private int order;
  private int leftNodeSize;
  private int rightNodeSize;
  private int middleNodePos;

  public static BTreeNodeFactory getNodeFactory(int order, BTreeKeyFactory keyFactory) {
    return new BTreeNodeFactory(order, keyFactory);
  }

  private BTreeNodeFactory(int order, BTreeKeyFactory keyFactory) {
    this.order = order;
    this.keyFactory = keyFactory;

    leftNodeSize = (order - 1) / 2;
    rightNodeSize = (order - 1) - leftNodeSize;
    middleNodePos = leftNodeSize;
  }

  public BTreeNode getNode() {
    return new BTreeNode(this);
  }

  public BTreeKey getNewKey(int value) {
    return keyFactory.getBTreeKey(value);
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
