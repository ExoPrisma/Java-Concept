package Data_Structure.Tree;

public interface Tree<T> {
  TreeNode<T> getRoot();
  int size();
  int height();
  int depth(T target);
  boolean insert(T element);
  boolean remove(T element);
  boolean contain(T element);
}
