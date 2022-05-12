package algorithm.btree;

/**
 * Describes one of the elements on a B-Tree node
 * 
 * @author Haroldo
 *
 */
class MedianElement {
  int key;
  MedianElement nextElement, previousElement;

  MedianElement(int key) {
    this.key = key;
  }
}