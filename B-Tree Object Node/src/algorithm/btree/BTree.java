package algorithm.btree;

/**
 * Implements a BTree solution.
 * 
 * This one accepts any kind of object to be stored and searched in the BTree.
 * 
 * The key must be an integer number.
 * 
 * @author Haroldo Macedo
 *
 */
public class BTree {

  private BTreeNode root;
  private BTreeNodeFactory nf;

  /**
   * 
   * @param order
   */
  public BTree(int order) {
    nf = new BTreeNodeFactory(order);
    root = nf.getNewNode();
  }

  /**
   * Add one object in the BTree.
   * 
   * @param value
   */
  public void addKey(final BTreeKey key) {
    root.add(key);
    if (!root.hasSpace()) {
      BTreeNode node = nf.getNewNode();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }
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
   * Returns the object with key 'key'.
   * 
   * @param key
   * @return
   */
  public BTreeKey getKey(final int key) {
    return root.getKey(key);
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
