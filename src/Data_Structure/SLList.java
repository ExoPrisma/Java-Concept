package Data_Structure;

import java.util.Objects;
import java.util.Iterator;
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
  *
  */
  @Override
  public boolean isEmpty() {
    return (this.size == 0);
  }

  /**
   * Get element at index
   * 
   * @param index
   * 
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

  /**
   * Set data of node at index to element
   * 
   * @param index
   * @param element
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

  /**
   * Add element to SLList
   * 
   * @param element
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

  /**
   * Add element at index
   * 
   * @param index
   * @param element
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

  /**
   * Remove node at index
   * 
   * @param index
   * 
   * @return removed data from node element
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

  /**
   * Remove first occurence of element
   * 
   * @param element
   * 
   * @return removed data from node element 
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

  /**
   * Remove all occurence of element
   * 
   * @param element
   * 
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

  /**
   * Check if element is contained 
   * 
   * @param element
   * 
   * @return true if element is contained  
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

  /**
   * Clone SLList
   * 
   * @return new deep copy of SLList 
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

  /**
   * Clear SLList 
   */
  @Override
  public void clear() {
    this.dummyHead.next = null;
    this.size = 0;
  }

  /**
   * Modify to string method to give [data1, data2, data3] format
   * 
   * @return string format for System.out.println();
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

  /**
   * Create and return iterator that iterates through SLList from the first node to the last
   * 
   * @return iterator that iterates through SLList from the first node to the last  
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
        if(hasNext()){
          current = current.next;
          return current.data;
        }
        return null;
      }
    };
  }

  /**
   * Compare two SLList depending on their size
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

  /**
   * Check if valid index else throw IndexOutOfBoundsException
   *  
   * @param index
   */
  private void isInvalidIndex(int index){
    if (index < 0 && index >= this.size){
      throw new IndexOutOfBoundsException(index + " is Index Out of Bound, List Size is " + this.size);
    }
  }

  /**
   * Add element to end of list with weight
   * 
   * @param element
   * @param weight 
   */
  public void add(T element, int weight) {
    Node<T> current = this.dummyHead;

    while(current.next != null){
      current = current.next;
    }
    current.next = new Node<T>(element, weight);
    this.size++;
  }

  /**
   * Add element at index with weight
   * 
   * @param index
   * @param element
   * @param weight
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

  public static void main(String[] args) {
  }
}
