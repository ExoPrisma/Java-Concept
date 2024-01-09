package Data_Structure.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class DLList<T> implements List<T>, Comparable<DLList<T>> {
  private final Node<T> dummyHead;
  private final Node<T> dummyTail;
  private int size;

  private static class Node<T> {
    T data;
    int weight;
    Node<T> next;
    Node<T> prev;

    Node(T data, int weight) {
      this.data = data;
      this.weight = weight;
      this.next = null;
      this.prev = null;
    }
  }

  public DLList(){
    this.dummyHead = new Node<T>(null, -1);
    this.dummyTail = new Node<T>(null, -1);
    this.dummyHead.next = this.dummyTail;
    this.dummyTail.prev = this.dummyHead;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return (this.size() == 0);
  }

  /** Get element at index
   * <p>
   * Starts at dummy head if the index is less than half the size, else starts at dummy tail
   * </p>
   */
  @Override
  public T get(int index) {
    isInvalidIndex(index);

    Node<T> current;

    if(index < size / 2){
      current = this.dummyHead.next;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    }
    else{
      current = this.dummyTail.prev;
      for (int i = size - 1; i > index; i--) {
        current = current.prev;
      }
    }
    return current.data;
  }

  /** Set element at index
   * <p>
   * Starts at dummy head if the index is less than half the size, else starts at dummy tail
   * </p>
   */
  @Override
  public void set(int index, T element) {
    isInvalidIndex(index);

    Node<T> current;

    if(index < this.size / 2){
      current = this.dummyHead.next;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    }
    else{
      current = this.dummyTail.prev;
      for (int i = this.size - 1; i > index; i--) {
        current = current.prev;
      }
    }
    current.data = element;
  }

  @Override
  public void add(T element) {
    Node<T> newNode = new Node<T>(element, -1);
    newNode.next = this.dummyTail;
    newNode.prev = this.dummyTail.prev;
    this.dummyTail.prev.next = newNode;
    this.dummyTail.prev = newNode;
    this.size++;
  }

  @Override
  public void add(int index, T element) {
    isInvalidIndex(index);

    Node<T> newNode = new Node<T>(element, -1);

    Node<T> current;

    if(index < this.size / 2){
      current = this.dummyHead.next;
      for(int i = 0; i < index; i++){
        current = current.next;
      }
    }
    else{
      current = this.dummyTail;
      for(int i = this.size - 1; i >= index; i--){
        current = current.prev;
      }
    }

    newNode.prev = current.prev;
    newNode.next = current;
    current.prev = newNode;

    this.size++;
  }

  @Override
  public void add(T element, int weight) {
    Node<T> newNode = new Node<T>(element, weight);
    newNode.next = this.dummyTail;
    newNode.prev = this.dummyTail.prev;
    this.dummyTail.prev.next = newNode;
    this.dummyTail.prev = newNode;
    this.size++;
  }

  @Override
  public void add(int index, T element, int weight) {
    isInvalidIndex(index);

    Node<T> newNode = new Node<T>(element, weight);

    Node<T> current;

    if(index < this.size / 2){
      current = this.dummyHead.next;
      for(int i = 0; i < index; i++){
        current = current.next;
      }
    }
    else{
      current = this.dummyTail.prev;
      for(int i = this.size - 1; i > index; i--){
        current = current.prev;
      }
    }

    newNode.prev = current.prev;
    newNode.next = current;
    current.prev = newNode;

    this.size++;
  }
  
  /** Remove element at index
   * <p>
   *  Starts at dummy head if the index is less than half the size, else starts at dummy tail
   * </p>
   */
  @Override
  public T remove(int index) {
    isInvalidIndex(index);

    Node<T> current;

    if(index < this.size / 2){
      current = this.dummyHead.next;
      for(int i = 0; i < index; i++){
        current = current.next;
      }
    }
    else{
      current = this.dummyTail.prev;
      for(int i = this.size - 1; i > index; i--){
        current = current.prev;
      }
    }

    current.prev.next = current.next;
    current.next.prev = current.prev;

    this.size--;

    return current.data;
  }

  @Override
  public T remove(T element) {
    Node<T> current = this.dummyHead;

    while(current != null){
      if(current.data == element){
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return current.data;
      }
      current = current.next;
    }
    return null;
  }

  @Override
  public int removeAll(T element) {
    Node<T> current = this.dummyHead;
    int count = 0;

    while(current != null){
      if(current.data == element){
        current.prev.next = current.next;
        current.next.prev = current.prev;
        count++;
      }
      current = current.next;
    }
    return count;
  }

  @Override
  public boolean contain(T element) {
    Node<T> current = this.dummyHead;

    while(current != null){
      if(current.data == element){
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  public List<T> clone() {
    DLList<T> newDLList = new DLList<T>();
    Node<T> current = this.dummyHead;

   while(current != null){
     newDLList.add(current.data, current.weight);
     current = current.next;
   }

    return newDLList;
  }

  @Override
  public void clear() {
    this.dummyHead.next = this.dummyTail;
    this.dummyTail.prev = this.dummyHead;
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
    Node<T> current = this.dummyHead.next;

    while (current != this.dummyTail) {
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
    else if(obj == null || obj.getClass() != this.getClass()){
      return false;
    }
    else{
      DLList<T> otherDlList = (DLList<T>) obj;

      Node<T> head1 = this.dummyHead;
      Node<T> head2 = otherDlList.dummyHead;

      while(head1 != null && head2 != null){
        if(head1.data != head2.data){
          return false;
        }
        head1 = head1.next;
        head2 = head2.next;
      }
      return true;
    }
    
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
        return current != null;
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
  public int compareTo(DLList<T> o) {
    return this.size() - o.size();
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
}

