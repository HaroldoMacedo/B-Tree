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
  private BTreeKey keys[];
  private BTreeNode children[];
  private BTreeNodeFactory nf;

  /**
   * 
   * @param nf
   */
  BTreeNode(BTreeNodeFactory nf) {
    this.nf = nf;
    keys = new BTreeKey[nf.getOrder()];
    children = new BTreeNode[nf.getOrder() + 1];
  }

  /**
   * Add a value to this node.
   * 
   * @param value
   */
  void add(int value) {
    // Find where to include the key.
    int pos = 0;
    for (pos = 0; pos < nodeSize && value > keys[pos].getKey(); pos++)
      ;

    //
    // Leaf node.
    if (isLeaf()) {
      // Open space to add the key and then add the key.
      for (int i = nodeSize - 1; i >= pos; i--)
        keys[i + 1] = keys[i];
      keys[pos] = nf.getNewKey(value);
      nodeSize++;

      return;
    }

    //
    // Not a leaf node.
    // Add the key to a child, and if the child node is full split it.
    children[pos].add(value);
    if (!children[pos].hasSpace())
      splitChildAt(pos);
  }

  /**
   * Search for a key and returns true if found.
   * 
   * @param value
   * @return
   */
  boolean hasKey(int value) {
    List<BTreeKey> keys = getKeysInNode();

    for (int pos = keys.size() - 1; pos >= 0; pos--) {
      if (value == keys.get(pos).getKey())
        return true;
      if (! isLeaf())
        if (value > keys.get(pos).getKey())
          return getChildInNodes().get(pos+1).hasKey(value);
    }

    if (isLeaf())
      return false;

    return getChildInNodes().get(0).hasKey(value);
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
  public List<BTreeKey> getKeysInNode() {
    List<BTreeKey> keyList = new ArrayList<BTreeKey>();
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
