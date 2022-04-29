package algorithm.btree;

public class Teste {

  public static void main(String[] args) {
//  int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10 };
    add(keys);
  }

  static void add(int[] args) {
    BTree b = new BTree();
    for (int i : args) {
      b.add(i);
      b.showNodes();
    }
  }
}
