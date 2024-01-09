package Data_Structure.List;

public interface List<T> extends Iterable<T>{

  /** Return size 
   *
   * @return integer size of list
   */ 
  int size();

  /** Return if list empty
  *
  * @return true if list empty
  */
  boolean isEmpty();

  /** Get element at index
   * 
   * @param index of element in list
   * @return element at index 
   */
  T get(int index);

  /** Set data at index
   * 
   * @param index of element in list
   * @param element that should be added to list
   */
  void set(int index, T element);

  /** Add element at end
   *
   * @param element that will be added to list
   */
  void add(T element);

  /** Add element at index
   * 
   * @param index of element to be added in list
   * @param element that will be added to list
   */
  void add(int index, T element);

  /** Add element at end with weight
   *
   * @param element that will be added to list
   * @param weight of element
   */
  void add(T element, int weight);

  /** Add element at index with weight
   *
   * @param index of element to be added in list
   * @param element that will be added to list
   * @param weight of element to be added in list
   */
  void add(int index, T element, int weight);

  /** Remove element at index
   *
   * @param index of element that will be removed
   * @return element that was removed
   */
  T remove(int index);

  /** Remove first occurence of element
   * 
   * @param element to be removed
   * @return element that is removed
   */
  T remove(T element);

  /** Remove all occurence of element
   *
   * @param element to be removed
   * @return number of element removed
   */
  int removeAll(T element);

  /** Contain element
   *
   * @param element searching for in list
   * @return true if element found
   */
  boolean contain(T element);

  /** Clone list
   *
   * @return copy of singly linked list
   */
  List<T> clone();

  /** Clear list
   *  
   */
  void clear();
}
