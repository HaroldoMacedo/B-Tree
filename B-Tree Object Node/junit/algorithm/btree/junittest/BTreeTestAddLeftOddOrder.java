package algorithm.btree.junittest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithm.btree.BTree;
import algorithm.btree.sample.BTreeIntegerKey;

class BTreeTestAddLeftOddOrder {

  private static BTree bt = new BTree(3);
  static int[] keysIn = new int[] { 100, 90, 80, 70, 60, 50, 40, 30, 20, 10 };
  static int[] keysNotIn = new int[] { 12, 13, 14, 15, 16, 17, 71, 81, 91, 101, 9};

  @BeforeAll
  static void init() {
    System.out.println("Initializing");
    for (int k : keysIn)
      bt.addKey(new BTreeIntegerKey(k));
  }

  @Test
  final void testKeySample() {
    System.out.println("testKeySample");
    for (int kIn : keysIn)
      for (int kNot : keysNotIn)
        assertNotEquals(kIn, kNot);
  }

  @Test
  final void testKeyFind() {
    System.out.println("testKeyFind");
    for (int k : keysIn)
      assertTrue(bt.isKeyInTree(k));
  }

  @Test
  final void testNoKeyFind() {
    System.out.println("testNoKeyFind");
    for (int k : keysNotIn)
      assertFalse(bt.isKeyInTree(k));
  }
}