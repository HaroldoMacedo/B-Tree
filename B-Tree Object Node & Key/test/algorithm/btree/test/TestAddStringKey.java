package algorithm.btree.test;

import algorithm.btree.BTree;

public class TestAddStringKey {

  public static void main(String[] args) {
//    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1" };
//    String[] keys = { "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };
    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };

    BTree b = new BTree(3);
    add(b, keys);
    
    keyInTree(b, new StringKey("1"));
    keyInTree(b, new StringKey("7"));
    keyInTree(b, new StringKey("61"));
    keyInTree(b, new StringKey("10"));
    keyInTree(b, new StringKey("11"));
    keyInTree(b, new StringKey("Julia"));
    keyInTree(b, new StringKey("julia"));
  }

  static void add(BTree b, String[] args) {
    for (String i : args) {
      b.addKey(new StringKey(i));
    }

  }

  static void keyInTree(BTree b, StringKey key) {
    System.out.printf("Key %s is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
  }

}
