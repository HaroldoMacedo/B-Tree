package test.btree;

import algorithm.btree.BTree;
import algorithm.btree.BTreeKey;
import algorithm.btree.sample.BTreeIntegerKey;

public class BTreeTest {

  public static void main(String[] args) {
//  int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//    int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18 };
//    int[] keys = { 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    add(keys);
  }

  static void add(int[] args) {
    BTree b = new BTree(3);
    for (int i : args) {
      b.addKey(new BTreeIntegerKey(i));
    }

    keyInTree(b, new BTreeIntegerKey(1));
    keyInTree(b, new BTreeIntegerKey(7));
    keyInTree(b, new BTreeIntegerKey(61));
    keyInTree(b, new BTreeIntegerKey(10));
    keyInTree(b, new BTreeIntegerKey(11));
  }
  
  static void keyInTree(BTree b, BTreeKey key) {
    System.out.printf("Key %s is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
  }
}
