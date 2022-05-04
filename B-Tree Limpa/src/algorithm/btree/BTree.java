package algorithm.btree;

/**
 * Implements a BTree solution
 * 
 * @author Haroldo Macedo
 *
 */
public class BTree {

  protected Node root;
  private Element middleElement = null;
  private boolean evenKeys = true;
  private BTreeNodeFactory nf;

  public BTree(int order) {
    nf = BTreeNodeFactory.getNodeFactory(3);
    root = nf.getNode();
  }

  /**
   * 
   * @param value
   */
  public void insert(final int value) {
    root.add(value);
    if (!root.hasSpace()) {
      Node node = nf.getNode();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }

    //  First key insertion in the BTree.
    if (middleElement == null) {
      middleElement = root.getElementsInNode().get(0);
      evenKeys = false;
      
      return;
    }

    evenKeys = !evenKeys;
    if (value > middleElement.key && !evenKeys)
      middleElement = middleElement.nextElement;
    if (value <= middleElement.key && evenKeys)
      middleElement = middleElement.previousElement;
  }

  public double calculateMedian() {
    if (evenKeys)
      return (double)(middleElement.key + middleElement.nextElement.key) / 2;
    else
      return middleElement.key;
  }

  public boolean isKeyInTree(final int key) {
    return root.hasKey(key);
  }

  public int getPreviousKey(int key) {
    return root.previousKey(key);
  }

  public int getNextKey(int key) {
    return root.nextKey(key);
  }
}
