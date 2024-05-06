package Data_Structure.Heap;

import java.util.Iterator;

import Data_Structure.List.List;
import Data_Structure.Tree.TreeNode;

public class MaxHeap<T extends Comparable<T>> extends DefaultHeap<T> {
  
  public MaxHeap(){
    super();
  }

  public MaxHeap(MaxHeap<T> maxHeap){
    super(maxHeap);
  }

  public MaxHeap(List<T> list){
    super(list);
  }

  @Override
  public boolean insert(T element) {
    super.insert(element);
    HeapNode<T> addedNode = findNode(getRoot(), element);
    maxHeapify(addedNode);
    return true;
  }
  private void maxHeapify(HeapNode<T> current) {
    
    while(current != this.getRoot() && current.data.compareTo(current.parent.data()) > 0){
      T temp = current.data();
      current.data = current.parent.data();
      current.parent.data = temp;
      current = current.parent;
    }
  }
  private HeapNode<T> findNode(HeapNode<T> current, T element) {
    if (current == null) {
        return null; 
    }

    if (current.data().compareTo(element) == 0) {
        return current; 
    }


    for (TreeNode<T> child : current.children()) {
        HeapNode<T> result = findNode((HeapNode<T>) child, element);
        if (result != null) {
            return result; 
        }
    }

    return null;
}

  @Override
  public T peek() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean remove() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean remove(T element) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean changeKey(T key, T newKey){
    return super.changeKey(key, newKey);
  }

  /** Merge two heaps
   * 
   * @param otherBSTree other heap that needs to be merged
   * @return merged heap
   */
  @Override
  public Heap<T> merge(Heap<T> otherBSTree){
    MaxHeap<T> mergeHeap = new MaxHeap<>();
    return super.merge(otherBSTree, mergeHeap);
  }

  @Override
  public List<T> toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString(){
    return super.toString();
  }

  public static void main(String[] args) {
    MaxHeap<Integer> a = new MaxHeap<>();
    a.insert(26);
    a.insert(14);
    a.insert(20);
    a.insert(24);
    a.insert(17);
    a.insert(19);
    a.insert(13);
    a.insert(12);
    a.insert(18);
    a.insert(11);
    System.out.println(a);
  }
}
