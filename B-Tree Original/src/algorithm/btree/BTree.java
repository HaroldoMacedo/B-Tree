package algorithm.btree;

import java.util.List;

public class BTree {

  private Node root;

  public BTree() {
    root = new Node();
  }

  /**
   * 
   * @param value
   */
  public void add(final int value) {
System.out.printf("Adding %02d\n", value);    
    root.add(value);
    if (! root.hasSpace()) {
      Node node = new Node();
      node.child[0] = root;
      root = node;
      node.splitChildAt(0);
//      node.add(value);
    }
  }

  /**
   * 
   */
  public boolean keyInTree(int key) {
    return root.nodeOfKey(key) != null;
  }
  

  /**
   * 
   */
  public void showKeys() {
    showKeys(root);
  }

  /**
   * 
   * @param node
   */
  private void showKeys(Node node) {
    if (node == null)
      return;

    List<Integer> keys = node.getKeysInNode();
    List<Node> childNodes = node.getChildInNodes();
    for (int i = 0; i < keys.size(); i++) {
      showKeys(childNodes.get(i));
      System.out.print(keys.get(i) + " ");
    }
    showKeys(childNodes.get(childNodes.size() - 1));
  }

  /**
   * 
   */
  public void showNodes() {
    showNodes(root);
  }

  private int level = 0;

  /**
   * 
   */
  public void showNodes(Node node) {
    if (node == null)
      return;

    System.out.printf("%2d ( ", ++level);
    for (Integer key : node.getKeysInNode()) {
      System.out.print(key + " ");
    }
    System.out.println(")");

    for (Node child : node.getChildInNodes()) {
      showNodes(child);
    }

    level--;
  }

  /**
   * @param value
   * @return
   */
  public void contains(int value) {
    System.out.printf("%d %s\n", value, (keyInTree(value) ? " Not Found" : " Exist"));
  }

}

