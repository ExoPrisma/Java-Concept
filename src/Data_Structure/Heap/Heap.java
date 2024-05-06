package Data_Structure.Heap;

import java.util.Iterator;

import Data_Structure.List.List;

public interface Heap<T>{
  int size();
  boolean isEmpty();
  boolean contain(T element);
  boolean insert(T element);
  boolean remove();
  boolean remove(T element);
  T peek();
  boolean changeKey(T key, T newKey);
  Heap<T> merge(Heap<T> otherBSTree);
  List<T> toArray();
  Iterator<T> iterator();
}
