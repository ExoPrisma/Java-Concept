package Data_Structure.Heap;

import java.util.Iterator;

import Data_Structure.List.DLList;
import Data_Structure.List.List;
import Data_Structure.Queue;
import Data_Structure.Tree.TreeNode;

public abstract class DefaultHeap<T extends Comparable<T>> implements Heap<T>, Iterable<T>{

  protected static class HeapNode<T> implements TreeNode<T>{

    T data;
    HeapNode<T> parent;
    HeapNode<T> leftChild;
    HeapNode<T> rightChild;

    public HeapNode(T pData){
      this.data = pData;
      this.parent = null;
      this.leftChild = null;
      this.rightChild = null;
    }

    public HeapNode(HeapNode<T> node){
      if(node == null){
        this.data = null;
        this.parent = null; 
        this.leftChild = null;
        this.rightChild = null;
      }
      else {
        this.data = node.data;
        this.parent = null;
        this.leftChild = (node.leftChild == null) ? null : new HeapNode<>(node.leftChild);
        this.rightChild = (node.rightChild == null) ? null : new HeapNode<>(node.rightChild);
      }
    }

    @Override
    public T data() {
      return this.data;
    }
    
    @Override
    public DLList<TreeNode<T>> children() {
      DLList<TreeNode<T>> children = new DLList<>();
      children.add(this.leftChild);
      children.add(this.rightChild);
      return children;
    }
    
    @Override
    public boolean hasChild() {
      for(TreeNode<T> child : this.children()){
        if(child != null){
          return true;
        }
      }
      return false;
    }

    @Override
    public TreeNode<T> parent() {
      return this.parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
      if(this == o){
        return true;
      }
      if(o == null || getClass() != o.getClass()){
        return false;
      }

      HeapNode<T> heapNode = (HeapNode<T>) o;

      return (this.data == heapNode.data);
    }
    
    @Override
    public String toString() {
      return this.data.toString();
    }
  } 

  private HeapNode<T> root;
  private int size;

  public DefaultHeap(){
    this.root = null;
    this.size = 0;
  }

  public DefaultHeap(DefaultHeap<T> heap){
    this.root = new HeapNode<>(heap.root);
    this.size = heap.size;
  }

  public DefaultHeap(List<T> list){
    for(T element : list){
      this.insert(element);
      this.size++;
    }
  } 

  /**
   * 
   * @return size of heap
   */
  @Override
  public int size(){
    return this.size;
  }

  /**
   * 
   * @return true if BSTree empty
   */
  @Override
  public boolean isEmpty(){
    return (this.size() == 0);
  }

  /** Search first occurence element
   * 
   * @param element to be searched for
   * @return true if search successful
   */
  @Override
  public boolean contain(T element) {
    return containHelper(this.root, element);
  }
  /** Search first occurence element from Heap with current root node
   * 
   * @param current root node of Heap
   * @param element to be searched for
   * @return true if search successful
   */
  private boolean containHelper(HeapNode<T> current, T element){
    if(current == null){
      return false;
    }
    if(element.compareTo(current.data) < 0) {
      return containHelper(current.leftChild, element);
    }
    else if(element.compareTo(current.data) > 0) {
      return containHelper(current.rightChild, element);
    }
    else{
      return true;
    }
  }

  /** Insert element into Heap
   * 
   * @param element that need to be inserted into Heap
   * @return true if insertion successful
   */
  @Override
  public boolean insert(T element){
    if(this.root == null){
      this.root = new HeapNode<>(element);
      this.size++;
      return true;
    }

    Queue<HeapNode<T>> queue = new Queue<>();
    queue.enqueue(this.root);

    while (!queue.isEmpty()) {
      HeapNode<T> current = queue.dequeue();

      if (current.leftChild == null) {
          current.leftChild = new HeapNode<>(element);
          current.leftChild.parent = current;
          this.size++;
          return true;
      } else {
        if(current.leftChild != null){
          queue.enqueue(current.leftChild);
        }
      }

      if (current.rightChild == null) {
          current.rightChild = new HeapNode<>(element);
          current.rightChild.parent = current;
          this.size++;
          return true;
      } else {
        if(current.rightChild != null){
          queue.enqueue(current.rightChild);
        }
      }
    }
  return false;
  }

  /** Remove root node
   * 
   * @return removed root node
   */
  public abstract boolean remove();

  /** Remove element from heap
   * 
   * @param element to be removed from heap
   * @return eleent removed from heap
   */
  public abstract boolean remove(T element);

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
  public boolean changeKey(T key, T newKey){
    if(this.root == null){
      return false;
    }

    Queue<HeapNode<T>> queue = new Queue<>();
    queue.enqueue(this.root);

    while(!queue.isEmpty()){
      HeapNode<T> current = queue.dequeue();

      if(key.compareTo(current.data()) == 0){
        current.data = newKey;
        return true;
      }

      if(current.leftChild != null){
        queue.enqueue(current.leftChild);
      }

      if(current.rightChild != null){
        queue.enqueue(current.rightChild);
      }
    }

    return false;
  }


  /** Merge two heaps
   * 
   * @param otherBSTree other heap that needs to be merged
   * @return merged heap
   */
  public Heap<T> merge(Heap<T> otherBSTree, Heap<T> mergeHeap){
    Iterator<T> iterator1 = this.iterator();
    Iterator<T> iterator2 = otherBSTree.iterator();

    while(iterator1.hasNext()){
      T element1 = iterator1.next();
      mergeHeap.insert(element1);
    }
    
    while(iterator2.hasNext()){
      T element2 = iterator2.next();
      mergeHeap.insert(element2);
    }
    
    return mergeHeap;
  }

  /** Flatten tree into array
   * 
   * @return flattened array of tree
   */
  public abstract List<T> toArray();

  /** Iterator of Heap
   * 
   * @return iterator over Heap for in order traversal
   */
  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub 
    return null;
  }

  /** Custom print 
   * <p>
   *   Format: (data, leftChild, RightChild)
   * </p>
   * 
   * @return print output
   */
  @Override
  public String toString() {
    return treeToString(this.root);
  }
  /** Custom print
   * <p>
   *   Format: (data, leftChild, RightChild)
   * </p>
   *
   * @param node of BST tree
   * @return print output
   */
  private String treeToString(HeapNode<T> node) {
    if (node == null) {
      return "null";
    }

    String leftStr = treeToString(node.leftChild);
    String rightStr = treeToString(node.rightChild);

    return "(" + node.data + ", " + leftStr + ", " + rightStr + ")";
  }

  /** Equality
   * 
   * @param obj to test equality
   * @return true if both BST contains same Node in same order
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
      DefaultHeap<T> otherHeap = (DefaultHeap<T>) obj;
      return equal(this.root, otherHeap.root);
    }
  }
  /** Equality between two BSTrees
  * 
  * @return true if two BSTree are the same
  */ 
  private boolean equal(HeapNode<T> node1, HeapNode<T> node2) {
    if (node1 == null && node2 == null) {
      return true;
    } 
    else if (node1 == null || node2 == null) {
      return false;
    } 
    else {
      return (node1.equals(node2)) &&
        equal(node1.leftChild, node2.leftChild) &&
        equal(node1.rightChild, node2.rightChild);
    }
  }

  /** 
   * 
   * @return root of Heap
   */
  public HeapNode<T> getRoot(){
    return this.root;
  }
}
