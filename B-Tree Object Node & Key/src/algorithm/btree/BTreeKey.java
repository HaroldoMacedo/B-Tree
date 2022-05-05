package algorithm.btree;

/**
 * Interface to implement to store any object in BTree.
 * 
 * @author Haroldo Macedo
 *
 */
public interface BTreeKey extends Comparable<BTreeKey> {

  public Object getKey();
}
