package Data_Structure.Iterator;

import java.util.Iterator;

import Data_Structure.Stack;
import Data_Structure.Tree.TreeNode;

public class InOrderIterator<T> implements Iterator<T>{

  TreeNode<T> root;
  Stack<TreeNode<T>> stack;

  public InOrderIterator(TreeNode<T> root){
    this.root = root;
    this.stack = new Stack<>();
    fillStack(this.root);
  }

  @Override
  public boolean hasNext() {
    return !this.stack.isEmpty();
  }

  @Override
  public T next() {
    return this.stack.pop().data();
  }

  private void fillStack(TreeNode<T> root){
    if(root == null){
      return;
    }

    int size = root.children().size();

    fillStack(root.children().get(size - 1));

    this.stack.push(root);

    for(int i = (size - 2); i >= 0; i--){
      fillStack(root.children().get(i));
    }
  }

}
