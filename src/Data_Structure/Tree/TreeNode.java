package Data_Structure.Tree;

import Data_Structure.DLList;

public interface TreeNode<T> {
  T data();
  TreeNode<T> parent();
  DLList<TreeNode<T>> children();
  boolean hasChild();
}
