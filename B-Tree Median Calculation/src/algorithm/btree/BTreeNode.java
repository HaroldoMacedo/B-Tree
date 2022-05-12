package algorithm.btree;

import java.util.List;

public interface BTreeNode {

  public List<Integer> getKeysInNode();

  public List<BTreeNodeImpl> getChildInNodes();
}
