package algorithm.btree.sample;

import algorithm.btree.BTreeKey;

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
