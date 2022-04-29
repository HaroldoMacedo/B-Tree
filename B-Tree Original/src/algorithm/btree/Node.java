package algorithm.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class that implements the node.
 * 
 * @author Haroldo
 *
 */
class Node {
  private static final int B_TREE_ORDER = 3;
  private static final int LEFT_NODE_SIZE = (B_TREE_ORDER - 1) / 2;
  private static final int RIGHT_NODE_SIZE = (B_TREE_ORDER - 1) - LEFT_NODE_SIZE;
  private static final int MIDDLE_NODE_POS = LEFT_NODE_SIZE;

  private int nodeSize = 0;
  int keys[] = new int[B_TREE_ORDER];
  Node child[] = new Node[B_TREE_ORDER + 1];

  /**
   * Returns the node where a key is located.
   * 
   * @param key
   * @return
   */
  public Node nodeOfKey(int key) {
    int i = 0;
    for (i = 0; i < nodeSize; i++) {
      if (key == keys[i])
        return this;

      if (key < keys[i])
        break;
    }

    if (child[i] != null)
      return child[i].nodeOfKey(key);
    else
      return null;
  }

  /**
   * Add a value to this node.
   * 
   * @param key
   */
  public void add(int key) {
    // Find where to include the key.
    int pos = 0;
    for (pos = 0; pos < nodeSize && key > keys[pos]; pos++)
      ;

    // Not a leaf node.
    if (!isLeaf()) {
      // Add the key to a child, and if the child node is full split it.
      child[pos].add(key);
      if (!child[pos].hasSpace())
        splitChildAt(pos);

      return;
    }

    // Leaf node.
    // Open space to add the key and then add the key.
    for (int i = nodeSize - 1; i >= pos; i--)
      keys[i + 1] = keys[i];
    keys[pos] = key;
    nodeSize++;
  }

  /**
   * 
   * @param pos
   */
  public void splitChildAt(int pos) {
    Node childToSplit = child[pos];
    //
    // Right side of the node.
    Node rightNode = new Node();
    rightNode.nodeSize = RIGHT_NODE_SIZE;
    // Copy the keys and children from the right side.
    for (int j = 0; j < RIGHT_NODE_SIZE; j++) {
      rightNode.keys[j] = childToSplit.keys[j + LEFT_NODE_SIZE + 1];

      // TODO Remove it.
      // To help debugging.
      childToSplit.keys[j + LEFT_NODE_SIZE + 1] = 0;
    }

    if (!childToSplit.isLeaf()) {
      for (int j = 0; j < RIGHT_NODE_SIZE + 1; j++) {
        rightNode.child[j] = childToSplit.child[j + LEFT_NODE_SIZE + 1];

        // TODO Remove it.
        // To help debugging.
        childToSplit.child[j + LEFT_NODE_SIZE + 1] = null;
      }
    }
    // Adjust left node size.
    childToSplit.nodeSize = LEFT_NODE_SIZE;

    // 
    //  Left side of the node.
    for (int j = nodeSize - 1; j >= pos; j--)
      // Shift keys and children to the right to open space to add new node as a child.
      keys[j + 1] = keys[j];
    
    if (! isLeaf()) {
      for (int j = nodeSize; j > pos; j--) 
        child[j + 1] = child[j];
    }

    //
    // Promote new node.
    child[pos + 1] = rightNode;
    keys[pos] = childToSplit.keys[MIDDLE_NODE_POS];

    // TODO Remove it.
    // To help debugging.
    childToSplit.keys[MIDDLE_NODE_POS] = 0;

    nodeSize++;
  }

  public List<Integer> getKeysInNode() {
    List<Integer> keyList = new ArrayList<Integer>();
    for (int i = 0; i < nodeSize; i++)
      keyList.add(keys[i]);

    return keyList;
  }

  public List<Node> getChildInNodes() {
    List<Node> childNodes = new ArrayList<Node>();
    for (int i = 0; i < nodeSize + 1; i++) {
      childNodes.add(child[i]);
    }

    return childNodes;
  }

  /**
   * If the node has space to store a new value.
   * 
   * @return
   */
  public boolean hasSpace() {
    return nodeSize < B_TREE_ORDER;
  }

  public boolean isLeaf() {
    return child[0] == null;
  }
}
