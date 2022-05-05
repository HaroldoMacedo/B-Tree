package test.btree;

import java.util.List;

import algorithm.btree.BTree;
import algorithm.btree.BTreeNode;


public class BTreePrintNodes extends BTree {

  public BTreePrintNodes(int order) {
    super(order);
  }

  public void showNodes() {
    showNodes(root);
  }

  private int level = 0;

  private void showNodes(BTreeNode node) {
    if (node == null)
      return;

    System.out.printf("%2d ( ", ++level);
    for (int key : node.getKeysInNode()) {
      System.out.print(key + " ");
    }
    System.out.println(")");

    for (BTreeNode child : node.getChildInNodes()) {
      showNodes(child);
    }

    level--;
  }

  public void showTree() {
    showChildrenTree(root);
  }

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

  private void printNodeKeys(List<Integer> keys) {
    System.out.print("( ");
    for (int k : keys) {
      System.out.print(k + " ");
    }
    System.out.print(") ");
  }

}
