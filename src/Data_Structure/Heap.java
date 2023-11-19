package Data_Structure;

import Data_Structure.List.DLList;
import Data_Structure.Tree.BSTree;

public abstract class Heap<T extends Comparable<T>>{
  BSTree<T> aBSTree;
  int size;

  public Heap(){
    this.aBSTree = new BSTree<>();
    this.size = 0;
  }

  //TODO create copy constructor

  //TODO create array constructor 

  /**
   * 
   * @return size of heap
   */
  public int size(){
    return this.size;
  }

  public boolean isEmpty(){
    return (this.size == 0);
  }

  /** Contain element
   * 
   * @param element searching for in heap
   * @return true if contained in heap
   */
  public boolean contain(T element){
    return (this.aBSTree.search(element));
  }

  /** Add key to heap 
   * 
   * @param key to be added to heap
   */
  public abstract void add(T key);

  /** Remove root node
   * 
   * @return removed root node
   */
  public abstract T remove();

  /** Remove element from heap
   * 
   * @param element to be removed from heap
   * @return eleent removed from heap
   */
  public abstract T remove(T element);

  /** Get root node data
   * 
   * @return root node data
   */
  public abstract T peek();

  /** Change key value
   * 
   * @param key that needs to be changed
   * @param newKey that replaces the old key
   * @return true if change successful
   */
  public abstract boolean changeKey(T key, T newKey);

  /** Merge two heaps
   * 
   * @param otherBSTree other heap that needs to be merged
   * @return merged heap
   */
  public abstract Heap<T> merge(Heap<T> otherBSTree);

  /** Flatten tree into array
   * 
   * @return flattened array of tree
   */
  public abstract DLList<T> toArray();

  /** Custom print
   * <p>
   *   Format: (data, leftChild, RightChild)
   * </p>
   *
   * @return print output
   */
  @Override 
  public String toString(){
    return (this.aBSTree.toString());
  }

  /** Equality
   * 
   * @param obj to test equality
   * @return true if both heap contains same items in same order
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
      return this.toArray().equals(((Heap<T>)obj).toArray())
    }
  }
}
