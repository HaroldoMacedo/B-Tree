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
    root = nf.getNewNode();
  }

  /**
   * Add one object in the BTree.
   * @param value
   */
  public void insert(final int value) {
    root.add(value);
    if (!root.hasSpace()) {
      BTreeNode node = nf.getNewNode();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }
  }
  
  /**
   * Check if a key is present in the BTree.
   * @param key
   * @return
   */
  public boolean isKeyInTree(final int key) {
    return root.hasKey(key);
  }
  
  /**
   * Returns the object with key 'key'.
   * @param key
   * @return
   */
  public int getKey(final int key) {
    return root.getKey(key);
  }
}
