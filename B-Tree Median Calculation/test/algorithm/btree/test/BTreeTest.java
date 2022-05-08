package algorithm.btree.test;

import algorithm.btree.BTree;

public class BTreeTest {

  public static void main(String[] args) {
//  int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//  int[] keys = {1, 1, 1, 1, 2, 3, 4, 5, 6};
//    int[] keys = {1, 100, 10, 20, 30, 90, 80, 70, 85};
//    int[] keys = {16, 18, 14, 12, 10, 11, 9, 8, 6, 4, 2, 20, 22, 24};
//    int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18 };
    int[] keys = { 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    add(keys);
  }

  static void add(int[] args) {
    BTreePrintNodes b = new BTreePrintNodes(3);
    for (int i : args) {
      b.addKey(i);
      b.showTree();
      System.out.println("\nMedian = " + b.calculateMedian());
    }

    keyInTree(b, 1);
    keyInTree(b, 7);
    keyInTree(b, 6);
    keyInTree(b, 4);
    keyInTree(b, 10);
    keyInTree(b, 11);
    keyInTree(b, 21);
    keyInTree(b, 12);
    keyInTree(b, 18);
    keyInTree(b, 24);
    keyInTree(b, 25);
    b.showTree();
  }
  
  static void keyInTree(BTree b, int key) {
    System.out.printf("Key %d is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
    System.out.printf("Previous key of key %d is %d.\n", key, b.getPreviousKey(key));
    System.out.printf("Next key of key %d is %d.\n", key, b.getNextKey(key));
  }
}