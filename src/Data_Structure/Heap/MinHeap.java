package Data_Structure.Heap;

import java.util.Iterator;

import Data_Structure.List.List;
import Data_Structure.Tree.TreeNode;

public class MinHeap<T extends Comparable<T>> extends DefaultHeap<T> {

  public MinHeap(){
    super();
  }

  public MinHeap(MinHeap<T> minHeap){
    super(minHeap);
  }

  public MinHeap(List<T> list){
    super(list);
  }

  @Override
  public boolean insert(T element) {
    super.insert(element);
    HeapNode<T> addedNode = findNode(getRoot(), element);
    minHeapify(addedNode);
    return true;
  }
  private void minHeapify(HeapNode<T> current) {
    
    while(current != this.getRoot() && current.data.compareTo(current.parent.data()) < 0){
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

  @Override
  public Heap<T> merge(Heap<T> otherBSTree) {
    MinHeap<T> mergeHeap = new MinHeap<>();
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
}