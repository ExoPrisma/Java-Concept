package Data_Structure;

import Data_Structure.List.DLList;

public class Queue<T> {
  private final DLList<T> queue;
  private int size;

  public Queue(){
    this.queue = new DLList<>();
    this.size = 0;
  }

  /**
   * 
   * @return size of stack
   */
  public int size(){
    return this.size;
  }

  /**
   * 
   * @return true if empty
   */
  public boolean isEmpty(){
    return (this.size == 0 && this.queue.isEmpty());
  }

  /** Enqueue element to queue
   * 
   * @param element to be added to queue
   */
  public void enqueue(T element){
    this.queue.add(element);
    this.size++;
  }

  /** Dequeue element from queue
   * 
   * @return element removed from queue
   */
  public T dequeue(){
    if(isEmpty()){
      System.out.println("The Queue is Empty, Dequeue Failed");
      return null;
    }

    this.size--;

    return this.queue.remove(0);
  }

  /** Peek next element
   * 
   * @return element first added to queue
   */
  public T peek(){
    return (this.queue.get(0));
  }

  /** Clear queue
   * 
   */
  public void clear(){
    this.queue.clear();
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
  public String toString(){
    return this.queue.toString();
  }

  public static void main(String[] args) {
    Queue<String> a = new Queue<>();
    a.enqueue("a");
    System.out.println(a.isEmpty());
    a.dequeue();
    System.out.println(a.isEmpty());
  }
}

