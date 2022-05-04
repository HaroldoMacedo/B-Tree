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
class Node implements BTreeNode {
  private int nodeSize = 0;
  private Element elements[];
  private Node children[];
  private BTreeNodeFactory nf;

  /**
   * 
   * @param nf
   */
  Node(BTreeNodeFactory nf) {
    this.nf = nf;
    elements = new Element[nf.getOrder()];
    children = new Node[nf.getOrder() + 1];
  }

  /**
   * Add a value to this node.
   * 
   * @param key
   */
  void add(int key) {
    // Find where to include the key.
    int pos = 0;
    for (pos = 0; pos < nodeSize && key > elements[pos].key; pos++)
      ;

    //
    // Leaf node.
    if (isLeaf()) {
      // Open space to add the key and then add the key.
      for (int i = nodeSize - 1; i >= pos; i--)
        elements[i + 1] = elements[i];
      elements[pos] = new Element(key);
      nodeSize++;

      // Update the double linked chain of previous and next elements.
      if (pos < nodeSize - 1) {
        elements[pos].nextElement = elements[pos + 1];
        elements[pos].previousElement = elements[pos + 1].previousElement;
      } else if (pos > 0) {
        elements[pos].previousElement = elements[pos - 1];
        elements[pos].nextElement = elements[pos - 1].nextElement;
      }

      if (elements[pos].nextElement != null) {
        elements[pos].nextElement.previousElement = elements[pos];
      }
      if (elements[pos].previousElement != null) {
        elements[pos].previousElement.nextElement = elements[pos];
      }

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
          return getChildInNodes().get(pos).hasKey(key);
    }

    if (isLeaf())
      return false;

    return getChildInNodes().get(keys.size()).hasKey(key);
  }

  /**
   * Returns the biggest key that is smaller than 'key'.
   * 
   * @param key
   * @return
   */
  int previousKey(int key) {
    List<Integer> keys = getKeysInNode();

    int pos = 0;
    for (; pos < keys.size(); pos++) {
      if (key <= keys.get(pos))
        break;
    }

    if (isLeaf()) {
      if (pos == 0)
        return -1;
      else
        return keys.get(pos - 1);
    }

    int previousKey = getChildInNodes().get(pos).previousKey(key);
    if (previousKey != -1)
      return previousKey;

    if (pos > 0)
      return keys.get(pos - 1);

    return -1;
  }

  /**
   * Returns the smallest key that is bigger than 'key'.
   * 
   * @param key
   * @return
   */
  int nextKey(int key) {
    List<Integer> keys = getKeysInNode();

    int pos = 0;
    for (; pos < keys.size(); pos++) {
      if (key < keys.get(pos))
        break;
    }

    if (isLeaf()) {
      if (pos == keys.size())
        return -1;
      else
        return keys.get(pos);
    }

    int nextKey = getChildInNodes().get(pos).nextKey(key);
    if (nextKey != -1)
      return nextKey;

    if (pos < keys.size())
      return keys.get(pos);

    return -1;
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
    Node rightNode = nf.getNode();
    rightNode.nodeSize = nf.getRightNodeSize();
    // Copy the keys from the right side.
    for (int j = 0; j < nf.getRightNodeSize(); j++) {
      rightNode.elements[j] = childToSplit.elements[j + nf.getLeftNodeSize() + 1];
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
      elements[j + 1] = elements[j];
    elements[pos] = childToSplit.elements[nf.getMiddleNodePos()];

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
  void setFirstChild(Node child) {
    this.children[0] = child;
  }

  /**
   * 
   * @return
   */
  List<Element> getElementsInNode() {
    List<Element> elementList = new ArrayList<Element>();
    for (int i = 0; i < nodeSize; i++)
      elementList.add(elements[i]);

    return elementList;
  }


  /**
   *
   * @return
   */
  @Override
  public List<Integer> getKeysInNode() {
    List<Integer> keyList = new ArrayList<Integer>();
    for (int i = 0; i < nodeSize; i++)
      keyList.add(elements[i].key);

    return keyList;
  }

  /**
   * 
   * @return
   */
  @Override
  public List<Node> getChildInNodes() {
    List<Node> childNodes = new ArrayList<Node>();
    for (int i = 0; i < nodeSize + 1; i++) {
      childNodes.add(children[i]);
    }

    return childNodes;
  }

  int getNodeSize() {
    return nodeSize;
  }
}

