package Data_Structure.List;

public interface List<T> extends Iterable<T>{

  int size();
  boolean isEmpty();
  T get(int index);
  void set(int index, T element);
  void add(T element);
  void add(int index, T element);
  T remove(int index);
  T remove(T element);
  int removeAll(T element);
  boolean contain(T element);
  List<T> clone();
  void clear();
}
