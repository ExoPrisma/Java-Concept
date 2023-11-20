//TODO FIX DEPTH within insert/deletion
//TODO FIX size within insert/deletion
package Data_Structure.Tree;

import Data_Structure.List.DLList;

public class BSTree<T extends Comparable<T>> implements Tree<T> {

  private static class BSTNode<T> implements TreeNode<T> {
  T data;
  int weight;
  int depth = -1;
  BSTNode<T> parent;
  BSTNode<T> leftChild;
  BSTNode<T> rightChild;

  public BSTNode(T pData){
    this.data = pData;
    this.weight = -1;
  }

  public BSTNode(T data, int weight){
    this.data = data;
    this.weight = weight;
  }

  public BSTNode(BSTNode<T> node){
    this.data = node.data;
    this.weight = node.weight;
    this.depth = node.depth;
    this.parent = new BSTNode<>(node.parent);
    this.leftChild = new BSTNode<>(node.leftChild);
    this.rightChild = new BSTNode<>(node.rightChild);
  }

  /**
   * 
   * @return data from node
   */
  @Override
  public T data() {
    return this.data;
  }

  /**
   * 
   * @return parent of node
   */
  @Override
  public TreeNode<T> parent() {
    return this.parent;
  }

  /**
   * 
   * @return left child
   */
  public BSTNode<T> getLeftChild(){
    return this.leftChild;
  }

  /**
   * 
   * @return right child
   */
  public BSTNode<T> getRightChild(){
    return this.rightChild;
  }

  /**
   * 
   * @return list of children of node
   */
  @Override
  public DLList<TreeNode<T>> children() {
    DLList<TreeNode<T>> children = new DLList<TreeNode<T>>();
    children.add(this.leftChild);
    children.add(this.rightChild);
    return children;
  }

  /**
   * 
   * @return true if has at least one child
   */
  @Override
  public boolean hasChild(){
    return (!children().isEmpty());
  }

  /** Equality
   * 
   * @param obj to test equality
   * @return true if node contain same data 
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o){
    if(this == o){
      return true;
    }
    if(o == null || getClass() != o.getClass()){
      return false;
    }

    BSTNode<T> bstNode = (BSTNode<T>) o;

    return (this.data == bstNode.data);
  }
}

  private BSTNode<T> root;

  public BSTree(){
    this.root = null;
  }

  public BSTree(T rootData){
    this.root = new BSTNode<>(rootData);
  }

  //TODO copy constructor

  /** Get height of BST
   * 
   * @return length of longest path from root of BST to a leaf
   */
  @Override
  public int height(){
    return heightHelper(this.root, 0);
  }
  /** Get height of BST from with root node
   * 
   * @param root node of the BST
   * @param currentHeight current height of BST root node
   * @return length of longest path from root of BST to a leaf
   */
  private int heightHelper(BSTNode<T> root, int currentHeight){
    if(root == null){
      return currentHeight;
    }

    int left = heightHelper(root.leftChild, currentHeight + 1);
    int right = heightHelper(root.rightChild, currentHeight + 1);

    return Math.max(left, right);
  }

  /** Get size of BST
   * 
   * @return size the number of node in BST
   */
  @Override
  public int size() {
    return sizeHelper(this.root);
  }
  /** Get size of BST from a node
   * 
   * @param current node of BST
   * @return size the number of node in BST
   */
  private int sizeHelper(BSTNode<T> current){
    if(current == null){
      return 0;
    }
    return (sizeHelper(current.leftChild) + sizeHelper(current.rightChild) + 1);
  }

  /** Get depth of target node
   * 
   * @return length path from first occurrence of target node to root
   */
  @Override
  public int depth(T target){
    return depthHelper(this.root, target, 0);
  }
  /** Get depth of a node
   * 
   * @param current node of tree
   * @param target node 
   * @param depth of current node
   * @return length path from first occurrence of target node to root
   *         -1 if target is null 
   */
  private int depthHelper(BSTNode<T> current, T target, int depth){
    if(target == null){
      return -1;
    }
    if(current.data == target){
      return depth;
    }

    int left = depthHelper(current.leftChild, target, depth + 1);
    int right = depthHelper(current.rightChild, target, depth + 1);
    return Math.max(left, right);
  }

  /** Insert element into BST
   * 
   * @param element that need to be inserted into BST
   * @return true if insertion successful
   */
  @Override
  public boolean insert(T element) {
    if(this.root == null){
      this.root = new BSTNode<>(element);
      return true;
    }
    return insertHelper(this.root, element);
  }
  /** Insert element into BS with root node current
   * 
   * @param current root node of BS
   * @param element to be inserted into BS
   * @return true if insertion successful 
   */
  private boolean insertHelper(BSTNode<T> current, T element){
    if(element.compareTo(current.data) == 0){
      return false;
    }
    else if(element.compareTo(current.data) < 0) {
      if(current.leftChild == null) {
        current.leftChild = new BSTNode<>(element);
        return true;
      }
      else {
        return insertHelper(current.leftChild, element);
      }
    }
    else {
      if(current.rightChild == null) {
        current.rightChild = new BSTNode<>(element);
        return true;
      }
      else {
        return insertHelper(current.rightChild, element);
      }
    }
  }

  /** Delete first occurence element 
   * 
   * @param element to be deleted
   * @return true if deletion successful
   */
  @Override
  public boolean delete(T element) {
    this.root = deleteHelper(this.root, element);
    return this.root != null;
  }
  /** Delete first occurence element of BST with current root node
   * <p>
   *  Minimum of right child taken to restore BST properties
   * </p>
   * 
   * @param current root node of BST
   * @param element to be deleted from BST
   * @return truee if deletion successful
   */
  private BSTNode<T> deleteHelper(BSTNode<T> current, T element){
    if(current == null){
      return null;
    }

    if(element.compareTo(current.data) < 0){
      current.leftChild = deleteHelper(current.leftChild, element);
    }
    else if(element.compareTo(current.data) > 0) {
      current.rightChild = deleteHelper(current.rightChild, element);
    }
    else{
      if(current.leftChild == null){
        return current.rightChild;
      }
      else if(current.rightChild == null){
        return current.leftChild;
      }
      current.data = minValue(current.rightChild);
      current.rightChild = deleteHelper(current.rightChild, current.data);
    }
    return current;
  }
  /** Find minimum value 
   * 
   * @param current node of BST 
   * @return minimum value
   */
  private T minValue(BSTNode<T> current){
    T min = current.data;
    while(current.leftChild != null){
      current = current.leftChild;
      min = current.data;
    }
    return min;
  }

  /** Search first occurence element
   * 
   * @param element to be searched for
   * @return true if search successful
   */
  @Override
  public boolean search(T element) {
    return searchHelper(this.root, element);
  }
  /** Search first occurence element from BST with current root node
   * 
   * @param current root node of BST
   * @param element to be searched for
   * @return true if search successful
   */
  private boolean searchHelper(BSTNode<T> current, T element){
    if(current == null){
      return false;
    }
    if(element.compareTo(current.data) < 0) {
      return searchHelper(current.leftChild, element);
    }
    else if(element.compareTo(current.data) > 0) {
      return searchHelper(current.rightChild, element);
    }
    else{
      return true;
    }
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
  /** Return tree as a string
   * <p>
   *   Format: (data, leftChild, RightChild)
   * </p>
   *
   * @param node of BST tree
   * @return print output
   */
  private String treeToString(BSTNode<T> node) {
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
      BSTree<T> otherTree = (BSTree<T>) obj;
      return equal(this.root, otherTree.root);
    }
  }
  /** Equality between two BSTrees
  * 
  * @return true if two BSTree are the same
  */ 
  private boolean equal(BSTNode<T> node1, BSTNode<T> node2) {
    if (node1 == null && node2 == null) {
      return true;
    } 
    else if (node1 == null || node2 == null) {
      return false;
    } 
    else {
      return (node1.equals(node2)) &&
        equal(node1.getLeftChild(), node2.getLeftChild()) &&
        equal(node1.getRightChild(), node2.getRightChild());
    }
  }
}
