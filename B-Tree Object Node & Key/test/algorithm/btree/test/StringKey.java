package algorithm.btree.test;

import algorithm.btree.BTreeKey;

class StringKey implements BTreeKey {

  private String key;

  StringKey(String key) {
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public int compareTo(BTreeKey oKey) {
    return key.compareTo(((StringKey) oKey).key);
  }

  @Override
  public String toString() {
    return key;
  }
}
