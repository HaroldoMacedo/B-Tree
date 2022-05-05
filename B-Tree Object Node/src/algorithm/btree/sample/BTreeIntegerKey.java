package algorithm.btree.sample;

import algorithm.btree.BTreeKey;

/**
 * Class that implements a sample on how to use the BTree to store a generic object.
 * 
 * @author Haroldo
 *
 */
public class BTreeIntegerKey implements BTreeKey {

  private int key;

  public BTreeIntegerKey(int key) {
    this.key = key;
  }

  @Override
  public int getKey() {
    return key;
  }

}
