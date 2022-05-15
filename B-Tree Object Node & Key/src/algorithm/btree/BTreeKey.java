package algorithm.btree;

/**
 * Interface to implement to store any object in BTree.
 * 
 * And, that the key can be of any type.
 * 
 * @author Haroldo Macedo
 *
 */
public interface BTreeKey extends Comparable<BTreeKey> {

  public Object getKey();
}
