package algorithm.btree.test;

public class BTreeTest {

  public static void main(String[] args) {
//  int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//  int[] keys = {1, 1, 1, 1, 2, 3, 4, 5, 6};
//    int[] keys = {1, 100, 10, 20, 30, 90, 80, 70, 85};
//    int[] keys = {16, 18, 14, 12, 10, 11, 9, 8, 6, 4, 2, 20, 22, 24};
//    int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18 };
    int[] keys = { 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    BTreePrintNodes bp = new BTreePrintNodes(3);
    for (int i : keys) {
      bp.addKey(i);
      bp.showTree();
      System.out.println("\nMedian = " + bp.calculateMedian());
    }
    bp.showTree();
    
  }
}
