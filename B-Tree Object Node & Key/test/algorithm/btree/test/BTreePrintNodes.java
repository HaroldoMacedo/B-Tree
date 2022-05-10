package algorithm.btree.test;

import java.util.List;

import algorithm.btree.BTree;
import algorithm.btree.BTreeKey;
import algorithm.btree.BTreeNode;

public class BTreePrintNodes extends BTree {

  /**
   * 
   * @param order
   */
  public BTreePrintNodes(int order) {
    super(order);
  }

  /**
   * 
   * @param key
   */
  public void keyInTree(BTreeKey key) {
    System.out.printf("Key %s is %s in tree.\n", key, (this.isKeyInTree(key) ? "" : "NOT"));
  }

  /**
   * Print all tree starting from the root node.
   * 
   */
  public void showTree() {
    showChildrenTree(this.getRoot());
  }

  /**
   * Print the tree from node 'node'.
   * 
   * @param node
   */
  private void showChildrenTree(BTreeNode node) {
    // Count levels
    int maxLevel = 1;

    for (BTreeNode n = node.getChildInNodes().get(0); n != null; n = n.getChildInNodes().get(0))
      maxLevel++;

    for (int l = 1; l <= maxLevel; l++) {
      System.out.printf("\nLevel %d ", l);
      printNodeLevel(l, node);
    }
  }

  /**
   * Print all nodes keys of this level.
   * 
   * @param level
   * @param node
   */
  private void printNodeLevel(int level, BTreeNode node) {
    // Print keys.
    if (level == 1) {
      printNodeKeys(node.getKeysInNode());
      return;
    }

    // Do down one level.
    level--;
    for (BTreeNode n : node.getChildInNodes())
      printNodeLevel(level, n);
  }

  /**
   * 
   * @param keys
   */
  private void printNodeKeys(List<BTreeKey> keys) {
    System.out.print("( ");
    for (BTreeKey k : keys) {
      System.out.print(k.getKey() + " ");
    }
    System.out.print(") ");
  }
}
