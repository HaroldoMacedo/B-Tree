package test.btree;

import algorithm.btree.BTree;
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

    keyInTree(b, 1);
    keyInTree(b, 7);
    keyInTree(b, 6);
    keyInTree(b, 10);
    keyInTree(b, 11);
  }
  
  static void keyInTree(BTree b, int key) {
    System.out.printf("Key %d is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
  }
}
