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

  public BTree(int order, BTreeKeyFactory keyFactory) {
    nf = BTreeNodeFactory.getNodeFactory(3, keyFactory);
    root = nf.getNewNode();
  }

  /**
   * 
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
  
  public boolean isKeyInTree(final int key) {
    return root.hasKey(key);
  }
}
