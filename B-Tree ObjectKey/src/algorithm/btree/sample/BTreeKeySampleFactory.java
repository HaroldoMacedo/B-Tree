package algorithm.btree.sample;

import algorithm.btree.BTreeKey;
import algorithm.btree.BTreeKeyFactory;

public class BTreeKeySampleFactory implements BTreeKeyFactory {

  @Override
  public BTreeKey getBTreeKey(int key) {
    return new BTreeKeySampleInteger(key);
  }
}
