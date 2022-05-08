package algorithm.btree.test;

import algorithm.btree.BTree;
import algorithm.btree.BTreeKey;

public class BTreeTestStringKey {

  public static void main(String[] args) {
//    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1" };
//    String[] keys = { "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };
    String[] keys = { "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "Julia", "Bruno", "Ricardo", "Ana Paula", "Haroldo" };
    add(keys);
  }

  static void add(String[] args) {
    BTree b = new BTree(3);
    for (String i : args) {
      b.addKey(new StringKey(i));
    }

    keyInTree(b, new StringKey("1"));
    keyInTree(b, new StringKey("7"));
    keyInTree(b, new StringKey("61"));
    keyInTree(b, new StringKey("10"));
    keyInTree(b, new StringKey("11"));
    keyInTree(b, new StringKey("Julia"));
    keyInTree(b, new StringKey("julia"));
  }

  static void keyInTree(BTree b, StringKey key) {
    System.out.printf("Key %s is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
  }

}

class StringKey implements BTreeKey {

  private String key;

  StringKey(String key) {
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public int compareTo(BTreeKey oKey) {
    return key.compareTo(((StringKey) oKey).key);
  }

  @Override
  public String toString() {
    return key;
  }
}
