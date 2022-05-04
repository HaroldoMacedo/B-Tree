package algorithm.btree;

class Element {
  int key;
  Element nextElement, previousElement;

  Element(int key) {
    this.key = key;
  }
}