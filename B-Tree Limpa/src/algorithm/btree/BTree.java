package algorithm.btree;

/**
 * Implements a BTree solution
 * 
 * @author Haroldo Macedo
 *
 */
public class BTree {

  protected BTreeNode root;
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
      BTreeNode node = nf.getNode();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }
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
