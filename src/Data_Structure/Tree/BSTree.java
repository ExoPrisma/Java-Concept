package Data_Structure.Tree;

import java.util.Iterator;

import Data_Structure.List.DLList;
import Data_Structure.Iterator.InOrderIterator;

public class BSTree<T extends Comparable<T>> implements Tree<T>, Iterable<T> {

  private static class BSTNode<T> implements TreeNode<T> {
    T data;
    int depth;
    BSTNode<T> parent;
    BSTNode<T> leftChild;
    BSTNode<T> rightChild;

    public BSTNode(T pData){
      this.data = pData;
      this.depth = 0;
      this.parent = null; 
      this.leftChild = null;
      this.rightChild = null;
    }

    public BSTNode(BSTNode<T> node){
      if(node == null){
        this.data = null;
        this.depth = 0;
        this.parent = null; 
        this.leftChild = null;
        this.rightChild = null;
      }
      else{
        this.data = node.data;
        this.depth = node.depth;
        this.parent = null; 
        this.leftChild = (node.leftChild == null) ? null : new BSTNode<>(node.leftChild);
        this.rightChild = (node.rightChild == null) ? null : new BSTNode<>(node.rightChild);
      }
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
      DLList<TreeNode<T>> children = new DLList<>();
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
  
    @Override
    public String toString(){
      return this.data.toString();
    }
  }
  
  private BSTNode<T> root;
  int size;

  public BSTree(){
    this.root = null;
    this.size = 0;
  }

  public BSTree(BSTree<T> tree){
    this.root = new BSTNode<>(tree.root);
    this.size = tree.size;
  }

  /** Get root of BST
   * 
   */
  @Override
  public TreeNode<T> getRoot(){
    return this.root;
  }

  /** Get size of BST
   * 
   * @return size the number of node in BST
   */
  @Override
  public int size() {
    return this.size;
  }

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
      this.root.depth = 0;
      return true;
    }
    this.size++;
    return insertHelper(this.root, element);
  }
  /** Insert element into BST with root node current
   * 
   * @param current root node of BST
   * @param element to be inserted into BST
   * @return true if insertion successful 
   */
  private boolean insertHelper(BSTNode<T> current, T element){
    if(element.compareTo(current.data) == 0){
      return false;
    }
    else if(element.compareTo(current.data) < 0) {
      if(current.leftChild == null) {
        current.leftChild = new BSTNode<>(element);
        current.leftChild.depth = this.depth(element);
        return true;
      }
      else {
        return insertHelper(current.leftChild, element);
      }
    }
    else {
      if(current.rightChild == null) {
        current.rightChild = new BSTNode<>(element);
        current.rightChild.depth = this.depth(element);
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
  public boolean remove(T element) {
    this.root = removeHelper(this.root, element);
    this.size--;
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
  private BSTNode<T> removeHelper(BSTNode<T> current, T element){
    if(current == null){
      return null;
    }

    if(element.compareTo(current.data) < 0){
      current.leftChild = removeHelper(current.leftChild, element);
    }
    else if(element.compareTo(current.data) > 0) {
      current.rightChild = removeHelper(current.rightChild, element);
    }
    else{
      if(current.leftChild == null){
        return current.rightChild;
      }
      else if(current.rightChild == null){
        return current.leftChild;
      }
      current.data = minValue(current.rightChild);
      current.rightChild = removeHelper(current.rightChild, current.data);
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
  public boolean contain(T element) {
    return containHelper(this.root, element);
  }
  /** Search first occurence element from BST with current root node
   * 
   * @param current root node of BST
   * @param element to be searched for
   * @return true if search successful
   */
  private boolean containHelper(BSTNode<T> current, T element){
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

  /** Iterator of BSTree
   * 
   * @return iterator over BSTree for in order traversal
   */
  @Override
  public Iterator<T> iterator() {
    return (Iterator<T>) new InOrderIterator<>(this.root);
  }
}
