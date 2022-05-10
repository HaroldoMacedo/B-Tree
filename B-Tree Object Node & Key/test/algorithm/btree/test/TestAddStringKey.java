package algorithm.btree.test;

import algorithm.btree.BTree;

public class TestAddStringKey {

  public static void main(String[] args) {
//    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1" };
//    String[] keys = { "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };
    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };

    BTreePrintNodes bp = new BTreePrintNodes(3);
    add(bp, keys);
    
    bp.keyInTree(new StringKey("1"));
    bp.keyInTree(new StringKey("7"));
    bp.keyInTree(new StringKey("61"));
    bp.keyInTree(new StringKey("10"));
    bp.keyInTree(new StringKey("11"));
    bp.keyInTree(new StringKey("Julia"));
    bp.keyInTree(new StringKey("julia"));
  }

  static void add(BTree b, String[] args) {
    for (String i : args) {
      b.addKey(new StringKey(i));
    }

  }
}
