package algorithm.btree.test;

import algorithm.btree.BTree;
import algorithm.btree.sample.BTreeIntegerKey;

public class TestAddKeys {

  /**
   * Test the BTree.
   * 
   * @param args
   */
  public static void main(String[] args) {
//  int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//    int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18 };
    int[] keys = { 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 3, 3,
        1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    BTreePrintNodes bp = new BTreePrintNodes(3);
    add(bp, keys);

    bp.keyInTree(1);
    bp.keyInTree(7);
    bp.keyInTree(6);
    bp.keyInTree(10);
    bp.keyInTree(11);
    bp.keyInTree(110);
    bp.keyInTree(0);
    bp.showTree();
  }

  /**
   * Adds an entire vector to the tree.
   * 
   * @param b
   * @param args
   */
  static void add(BTree b, int[] args) {
    for (int i : args) {
      b.addKey(new BTreeIntegerKey(i));
    }
  }
}
