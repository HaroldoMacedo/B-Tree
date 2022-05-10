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
  private boolean evenElements = true;
  private BTreeNodeFactory nf;

  /**
   * 
   * @param order
   */
  public BTree(int order) {
    nf = new BTreeNodeFactory(3);
    root = nf.getNode();
  }

  /**
   * Add one object in the BTree.
   * 
   * @param key
   */
  public void addKey(final int key) {
    root.add(key);
    if (!root.hasSpace()) {
      Node node = nf.getNode();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }

    //  First key insertion in the BTree.
    if (middleElement == null) {
      middleElement = root.getElementsInNode().get(0);
      evenElements = false;
      
      return;
    }

    evenElements = !evenElements;
    if (key > middleElement.key && !evenElements)
      middleElement = middleElement.nextElement;
    if (key <= middleElement.key && evenElements)
      middleElement = middleElement.previousElement;
  }

  /**
   * 
   * @return
   */
  public double calculateMedian() {
    if (evenElements)
      return (double)(middleElement.key + middleElement.nextElement.key) / 2;
    else
      return middleElement.key;
  }

  /**
   * Check if a key is present in the BTree.
   * 
   * @param key
   * @return
   */
  public boolean isKeyInTree(final int key) {
    return root.hasKey(key);
  }

  /**
   * 
   * @param key
   * @return
   */
  public int getPreviousKey(int key) {
    return root.previousKey(key);
  }

  /**
   * 
   * @param key
   * @return
   */
  public int getNextKey(int key) {
    return root.nextKey(key);
  }

  /**
   * To enable extensions for test and validation purposes.
   * 
   * @return
   */
  protected BTreeNode getRoot() {
    return root;
  }
}
