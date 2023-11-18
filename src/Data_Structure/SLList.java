package Data_Structure;

import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SLList<T> implements List<T>, Iterable<T>, Comparable<SLList<T>>{

  private final Node<T> dummyHead;
  private int size;

  private static class Node<T>{
    T data;
    int weight;
    Node<T> next;

    Node(T data, int weight) {
      this.data = data;
      this.weight = weight;
      this.next = null;
    }
  }

  public SLList() {
    this.dummyHead = new Node<T>(null, -1);
    this.size = 0;
  }

  /**
   *
   * @return size of doubly linked list
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
  *
  * @return true if doubly linked list empty
  */
  @Override
  public boolean isEmpty() {
    return (this.size == 0);
  }

  /** Get element at index
   * 
   * @param index of element in singly linked list
   * @return element at index 
   */
  @Override
  public T get(int index) {
    isInvalidIndex(index);

    Node<T> current = this.dummyHead.next;

    for(int i = 0; i < index - 1; i++) {
      current = current.next;
    }

    return current.data;
  }

  /** Set data at index
   * 
   * @param index of element in doubly linked list
   * @param element that should be added to list
   */
  @Override
  public void set(int index, T element) {
    isInvalidIndex(index);

    Node<T> current = this.dummyHead.next;

    for(int i = 0; i < index; i++) {
      current = current.next;
    }

    current.data = element;
  }

  /** Add element at end
   * 
   * @param element that will be added to list
   */
  @Override
  public void add(T element) {

    Node<T> current = this.dummyHead;

    while(current.next != null){
      current = current.next;
    }
    current.next = new Node<T>(element, -1);
    this.size++;
  }

  /** Add element at index
   * 
   * @param index of element to be added in list
   * @param element that will be added to list
   */
  @Override
  public void add(int index, T element) {
    isInvalidIndex(index);

    Node<T> current = this.dummyHead;
    for(int i = 0; i < index; i++){
      current = current.next;
    }

    Node<T> temp = current.next;
    current.next = new Node<T>(element, -1);
    current.next.next = temp;
    this.size++;
  }

  /** Remove element at index
   *
   *  
   * @param index of element that will be removed
   * @return element that was removed
   */
  @Override
  public T remove(int index) {
    isInvalidIndex(index);

    Node<T> current = this.dummyHead;

    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    Node<T> temp = current.next;
    current.next = current.next.next;
    temp.next = null;

    this.size--;

    return temp.data;
  }

  /** Remove first occurence of element
   * 
   * @param element to be removed
   * @return element that is removed
   */
  @Override
  public T remove(T element) {
    
    Node<T> current = this.dummyHead;
    Node<T> previous = null;

    while (current != null) {
      if (current.data.equals(element)) {
        Objects.requireNonNullElse(previous, this.dummyHead).next = current.next;
        this.size--;
        return current.data;
      }
      previous = current;
      current = current.next;
    }

    return null;
  }

  /** Remove all occurence of element
   * 
   * @param element to be removed
   * @return number of element removed
   */
  @Override
  public int removeAll(T element) {

    Node<T> current = this.dummyHead.next;
    Node<T> previous = this.dummyHead;
    int count = 0;

    while (current != null) {
      if (current.data.equals(element)) {
        previous.next = current.next;

        current = current.next;

        count++;
        this.size--;
      } else {
        previous = current;
        current = current.next;
      }
    }

    return count;
  }

  /** Contain element
   *
   * @param element searching for in list
   * @return true if element found
   */
  @Override
  public boolean contain(T element) {

    Node<T> current = this.dummyHead.next;

    while(current != null){
      if(current.data.equals(element)){
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /** Clone list
   *
   * @return copy of singly linked list
   */
  @Override
  public List<T> clone() {
    SLList<T> newSLList = new SLList<>();
    Node<T> current = this.dummyHead.next;

    while(current != null){
      newSLList.add(current.data, current.weight);
      current = current.next;
    }

    return newSLList;
  }

  /** Clear list
   *  
   */
  @Override
  public void clear() {
    this.dummyHead.next = null;
    this.size = 0;
  }

  /** Custom print
   * <p>
   *   Format: [x1, x2, ....]
   * </p>
   *
   * @return print output
   */
  @Override
  public String toString() {
    StringJoiner list = new StringJoiner(", ", "[", "]");
    Node<T> current = dummyHead.next;

    while(current != null){
      if(current.data == null){
        list.add(null);
      }
      else{
        list.add(current.data.toString());
      }
      current = current.next;
    }

    return list.toString();
  }

  /** Equality
   * 
   * @param obj to test equality
   * @return true if both list contains same items in same order
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object obj){
    if(this == obj){
      return true;
    }
    if(obj == null || obj.getClass() != this.getClass()){
      return false;
    }
    if(this.size() != ((SLList<T>) obj).size()){
      return false;
    }
    
    SLList<T> sllist = (SLList<T>) obj;
    Node<T> head1 = this.dummyHead;
    Node<T> head2 = sllist.dummyHead;

    while(head1 != null && head2 != null){
      if(head1.data != head2.data){
        return false;
      }
      head1 = head1.next;
      head2 = head2.next;
    }

    return true;

  }

  /** Return iterator
   * <p>
   *  Iterates through SLList from the first node to the last
   * </p>
   * @return iterator on list
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Node<T> current = dummyHead.next;
      @Override
      public boolean hasNext() {
        return current.next != null;
      }

      @Override
      public T next() {
        if(!hasNext()){
          throw new NoSuchElementException("No next element");
        }
        T data = current.data;
        current = current.next;
        return data;
      }
    };
  }

  /** Comparison
   * <p>
   *  Comparison by list size
   * </p>
   * @param o object to be compared
   * @return 0 if equal, > if bigger, < if smaller
   */
  @Override
  public int compareTo(SLList<T> o) {
    if(this.size() > o.size()){
      return 1;
    }
    else if(this.size() < o.size()){
      return -1;
    }
    return 0;
  }

  /** Validate index
   * <p>
   *  If invalid then throw IndexOutOfBoundsException
   * </p>
   * @param index of element in list
   */
  private void isInvalidIndex(int index){
    if (index < 0 && index >= this.size){
      throw new IndexOutOfBoundsException(index + " is Index Out of Bound, List Size is " + this.size);
    }
  }

  /** Add element at end with weight
   *
   * @param element that will be added to list
   * @param weight of element
   */
  public void add(T element, int weight) {
    Node<T> current = this.dummyHead;

    while(current.next != null){
      current = current.next;
    }
    current.next = new Node<T>(element, weight);
    this.size++;
  }

  /** Add element at index with weight
   *
   * @param index of element to be added in list
   * @param element that will be added to list
   * @param weight of element to be added in list
   */
  public void add(int index, T element, int weight) {
    isInvalidIndex(index);

    Node<T> current = this.dummyHead;
    for(int i = 0; i < index; i++){
      current = current.next;
    }

    Node<T> temp = current.next;
    current.next = new Node<T>(element, weight);
    current.next.next = temp;
    this.size++;
  }
}
