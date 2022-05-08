package algorithm.btree;

/**
 * Describes one of the elements on a B-Tree node
 * 
 * @author Haroldo
 *
 */
class Element {
  int key;
  Element nextElement, previousElement;

  Element(int key) {
    this.key = key;
  }
}