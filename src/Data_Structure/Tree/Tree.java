package Data_Structure.Tree;

public interface Tree<T> {
  int height();
  int size();
  int depth(T target);
  boolean insert(T element);
  boolean delete(T element);
  boolean search(T element);
}
