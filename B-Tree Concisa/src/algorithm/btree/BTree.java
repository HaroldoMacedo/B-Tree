package algorithm.btree;

import java.util.ArrayList;
import java.util.List;

public class BTree {
  public Node root = new Node();

  /**
   * Add a node into the structure sorted by the number 'n'.
   * 
   * @param n
   */
  public void addKey(int key) {
    root.add(key);
    if (!root.hasSpace()) {
      Node node = new Node();
      node.setFirstChild(root);
      root = node;
      node.splitChildAt(0);
    }
  }

  public boolean isKeyInTree(final int key) {
    return root.hasKey(key);
  }
}

/**
 * Node for the node structure.
 * 
 * @author Haroldo
 */
class Node {
  private int nodeSize = 0;
  private int keys[];
  private Node children[];
  private static final int ORDER = 3;
  private static final int LEFT_NODE_SIZE = (ORDER - 1) / 2;
  private static final int RIGHT_NODE_SIZE = (ORDER - 1) - LEFT_NODE_SIZE;
  private static final int MIDDLE_NODE_SIZE = LEFT_NODE_SIZE;

  public Node() {
    keys = new int[ORDER];
    children = new Node[ORDER + 1];
  }

  /**
   * Add a value to this node.
   * 
   * @param key
   */
  void add(int key) {
    // Find where to include the key.
    int pos = 0;
    for (pos = 0; pos < nodeSize && key > keys[pos]; pos++)
      ;

    //
    // Leaf node.
    if (isLeaf()) {
      // Open space to add the key and then add the key.
      for (int i = nodeSize - 1; i >= pos; i--)
        keys[i + 1] = keys[i];
      keys[pos] = key;
      nodeSize++;

      return;
    }

    //
    // Not a leaf node.
    // Add the key to a child, and if the child node is full split it.
    children[pos].add(key);
    if (!children[pos].hasSpace())
      splitChildAt(pos);

  }

  /**
   * Search for a key and returns true if found.
   * 
   * @param key
   * @return
   */
  boolean hasKey(int key) {
    List<Integer> keys = getKeysInNode();

    for (int pos = 0; pos < keys.size(); pos++) {
      if (key == keys.get(pos))
        return true;

      if (!isLeaf())
        if (key < keys.get(pos))
          return getChildrenInNode().get(pos).hasKey(key);
    }

    if (isLeaf())
      return false;

    return getChildrenInNode().get(keys.size()).hasKey(key);
  }

  /**
   * 
   * @param pos
   */
  void splitChildAt(int pos) {
    Node childToSplit = children[pos];

    //
    // Right side of the node.
    ///////////////////////////////////
    Node rightNode = new Node();

    rightNode.nodeSize = RIGHT_NODE_SIZE;
    // Copy the keys from the right side.
    for (int j = 0; j < RIGHT_NODE_SIZE; j++) {
      rightNode.keys[j] = childToSplit.keys[j + LEFT_NODE_SIZE + 1];
    }

    if (!childToSplit.isLeaf()) {
      // Copy the children from the right side.
      for (int j = 0; j < RIGHT_NODE_SIZE + 1; j++) {
        rightNode.children[j] = childToSplit.children[j + LEFT_NODE_SIZE + 1];
      }
    }

    //
    // Left side of the node.
    ///////////////////////////////////
    // Adjust left node size.
    childToSplit.nodeSize = LEFT_NODE_SIZE;

    //
    // Promote the middle key.
    ///////////////////////////////////
    // Shift keys to the right to open space to add the promoted key.
    for (int j = nodeSize - 1; j >= pos; j--)
      keys[j + 1] = keys[j];
    keys[pos] = childToSplit.keys[MIDDLE_NODE_SIZE];

    if (!isLeaf()) {
      // Shift children to the right to open space to add new node as a child.
      for (int j = nodeSize; j > pos; j--)
        children[j + 1] = children[j];
    }
    children[pos + 1] = rightNode;

    // Increase the nodesize because the promoted key is added to the node.
    nodeSize++;

  }

  /**
   * If the node has space to store a new key.
   * 
   * @return
   */
  boolean hasSpace() {
    return nodeSize < ORDER;
  }

  /**
   * Check if this node is a leaf or an internal node.
   * 
   * @return
   */
  boolean isLeaf() {
    return children[0] == null;
  }

  /**
   * Used for splitting root nodes.
   * 
   * @param child
   */
  void setFirstChild(Node child) {
    this.children[0] = child;
  }

  /**
   * 
   * @return
   */
  List<Node> getChildrenInNode() {
    List<Node> childrenInNode = new ArrayList<Node>();
    for (int i = 0; i < nodeSize + 1; i++) {
      childrenInNode.add(children[i]);
    }

    return childrenInNode;
  }

  /**
   *
   * @return
   */
  public List<Integer> getKeysInNode() {
    List<Integer> keyList = new ArrayList<Integer>();
    for (int i = 0; i < nodeSize; i++)
      keyList.add(keys[i]);

    return keyList;
  }
}

///**
// * Testing class.
// * 
// * @author Haroldo
// *
// */
//class Teste1 {
//  private static int level = 0;
//
//  public static void main(String[] args) {
//    int[] keys = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
////  int[] keys = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
////  int[] keys = {1, 1, 1, 1, 2, 3, 4, 5, 6};
////  int[] keys = {1, 100, 10, 20, 30, 90, 80, 70, 85};
////  int[] keys = {16, 18, 14, 12, 10, 11, 9, 8, 6, 4, 2, 20, 22, 24};
////  int[] keys = { 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18 };
////    int[] keys = { 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 3, 3, 3, 1, 2, 3, 11, 5, 6, 7, 4, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//    add(keys);
//  }
//
//  static void add(int[] args) {
//    BTree b = new BTree();
//    for (int i : args) {
//      b.addKey(i);
//      showNodes(b.root);
//    }
//
//    keyInTree(b, 1);
//    keyInTree(b, 7);
//    keyInTree(b, 6);
//    keyInTree(b, 4);
//    keyInTree(b, 10);
//    keyInTree(b, 11);
//    keyInTree(b, 21);
//    keyInTree(b, 12);
//    keyInTree(b, 18);
//    keyInTree(b, 24);
//    keyInTree(b, 25);
//    showChildrenTree(b.root);
//  }
//
//  static void keyInTree(BTree b, int key) {
//    System.out.printf("Key %d is %s in tree.\n", key, (b.isKeyInTree(key) ? "" : "NOT"));
//  }
//
//  static void showChildrenTree(Node node) {
//    // Count levels
//    int maxLevel = 1;
//
//    for (Node n = node.getChildrenInNode().get(0); n != null; n = n.getChildrenInNode().get(0))
//      maxLevel++;
//
//    for (int l = 1; l <= maxLevel; l++) {
//      System.out.printf("\nLevel %d ", l);
//      printNodeLevel(l, node);
//    }
//  }
//
//  static void printNodeLevel(int level, Node node) {
//    // Print keys.
//    if (level == 1) {
//      printNodeKeys(node.getKeysInNode());
//      return;
//    }
//
//    // Do down one level.
//    level--;
//    for (Node n : node.getChildrenInNode())
//      printNodeLevel(level, n);
//  }
//
//  static void printNodeKeys(List<Integer> keys) {
//    System.out.print("( ");
//    for (int k : keys) {
//      System.out.print(k + " ");
//    }
//    System.out.print(") ");
//  }
//
//  static void showNodes(Node node) {
//    if (node == null)
//      return;
//
//    System.out.printf("%2d ( ", ++level);
//    for (int key : node.getKeysInNode()) {
//      System.out.print(key + " ");
//    }
//    System.out.println(")");
//
//    for (Node child : node.getChildrenInNode()) {
//      showNodes(child);
//    }
//
//    level--;
//  }
//}
