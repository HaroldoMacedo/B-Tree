package algorithm.btree.sample;

import algorithm.btree.BTreeKey;
import algorithm.btree.BTreeKeyFactory;

public class BTreeIntegerKeyFactory implements BTreeKeyFactory {

  @Override
  public BTreeKey getBTreeKey(int key) {
    return new BTreeIntegerKey(key);
  }
}
