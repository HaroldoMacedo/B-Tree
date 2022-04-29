package algorithm.btree.sample;

import algorithm.btree.BTreeKey;

public class BTreeKeySampleInteger implements BTreeKey {

  private int key;
  public BTreeKeySampleInteger(int key) {
    this.key = key;
  }

  @Override
  public int getKey() {
    return key;
  }

}
