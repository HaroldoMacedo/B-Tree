package algorithm.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class that implements the BTree node.
 * 
 * @author Haroldo Macedo
 *
 */
public class BTreeNode {
  private int nodeSize = 0;
  private int keys[];
  private BTreeNode children[];
  private BTreeNodeFactory nf;

  /**
   * 
   * @param nf
   */
  BTreeNode(BTreeNodeFactory nf) {
    this.nf = nf;
    keys = new int[nf.getOrder()];
    children = new BTreeNode[nf.getOrder() + 1];
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
    return getKey(key) != Integer.MIN_VALUE;
  }
  
  /**
   * Returns the key, or Integer.MIN_VALUE if not found.
   * @param value
   * @return
   */
  int getKey(final int key) {
    List<Integer> keys = getKeysInNode();

    for (int pos = keys.size() - 1; pos >= 0; pos--) {
      if (key == keys.get(pos))
        return keys.get(pos);
      if (! isLeaf())
        if (key > keys.get(pos))
          return getChildInNodes().get(pos+1).getKey(key);
    }

    if (isLeaf())
      return Integer.MIN_VALUE;

    return getChildInNodes().get(0).getKey(key);
  }

  /**
   * 
   * @param pos
   */
  void splitChildAt(int pos) {
    BTreeNode childToSplit = children[pos];

    //
    // Right side of the node.
    ///////////////////////////////////
    BTreeNode rightNode = nf.getNewNode();
    rightNode.nodeSize = nf.getRightNodeSize();
    // Copy the keys from the right side.
    for (int j = 0; j < nf.getRightNodeSize(); j++) {
      rightNode.keys[j] = childToSplit.keys[j + nf.getLeftNodeSize() + 1];
    }

    if (!childToSplit.isLeaf()) {
      // Copy the children from the right side.
      for (int j = 0; j < nf.getRightNodeSize() + 1; j++) {
        rightNode.children[j] = childToSplit.children[j + nf.getLeftNodeSize() + 1];
      }
    }

    //
    // Left side of the node.
    ///////////////////////////////////
    // Adjust left node size.
    childToSplit.nodeSize = nf.getLeftNodeSize();

    //
    // Promote the middle key.
    ///////////////////////////////////
    // Shift keys to the right to open space to add the promoted key.
    for (int j = nodeSize - 1; j >= pos; j--)
      keys[j + 1] = keys[j];
    keys[pos] = childToSplit.keys[nf.getMiddleNodePos()];

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
    return nodeSize < nf.getOrder();
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
  void setFirstChild(BTreeNode child) {
    this.children[0] = child;
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

  /**
   * 
   * @return
   */
  public List<BTreeNode> getChildInNodes() {
    List<BTreeNode> childNodes = new ArrayList<BTreeNode>();
    for (int i = 0; i < nodeSize + 1; i++) {
      childNodes.add(children[i]);
    }

    return childNodes;
  }
}
