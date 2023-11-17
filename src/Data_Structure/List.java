package Data_Structure;

public interface List<T> {

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
