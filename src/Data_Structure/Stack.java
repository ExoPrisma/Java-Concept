package Data_Structure;

public class Stack<T> {
  private final DLList<T> stack;
  private int size;

  public Stack(){
    this.stack = new DLList<>();
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
   * @return true if stack empty
   */
  public boolean isEmpty(){
    return (this.size == 0);
  }

  /** Push element on stack
   * 
   * @param element to be added to stack
   */
  public void push(T element){
    this.stack.add(element);
    this.size++;
  }

  /** Pop element from stack
   * 
   * @return element removed from stack
   */
  public T pop(){
    if(isEmpty()){
      System.out.println("The Stack is Empty, Pop failed");
      return null;
    }

    this.size--;

    return this.stack.remove(this.size);
  }

  /** Peek top of stack 
   * 
   * @return last element added to stack
   */
  public T peek(){
    return stack.get(this.size);
  }

  /** Clear stack
   * 
   */
  public void clear(){
    this.stack.clear();
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
    return stack.toString();
  }
}

