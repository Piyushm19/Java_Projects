package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;

  private double log32(int i) {
    return Math.log(upperBound) / Math.log(3.0 / 2.0);
  }

  @Override
  public void add(T t) {
    // TODO
    BSTNode<T> node = new BSTNode<T>(t, null, null);
    root = addToSubtree(root, node);
    upperBound++;
    if (height() > log32(upperBound)) {
      node = node.getParent();
      while (3 * subtreeSize(node) <= 2 * subtreeSize(node.getParent())) {
        node = node.getParent();
      }
      node = node.getParent();
      BSTNode<T> parent = node.getParent();
      BinarySearchTree<T> tempTree = new BinarySearchTree<T>();
      Iterator<T> result = inorderHelper(node);
      while (result.hasNext()) {
        tempTree.add(result.next());
      }
      tempTree.balance();
      if (parent.getLeft() == node) {
        parent.setLeft(tempTree.root);
      } else {
        parent.setRight(tempTree.root);
      }
    }
  }

  @Override
  public boolean remove(T element) {
    // TODO
    if (super.remove(element)) {
      if (2 * size() < upperBound) {
        balance();
        upperBound = size();
      }
      return true;
    }
    return false;
  }
}
