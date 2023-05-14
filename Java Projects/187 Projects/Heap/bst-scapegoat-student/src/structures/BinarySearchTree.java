package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public T getFromTree(T t, BSTNode<T> node) {
    if (node == null) {
      return null;
    }
    if (t.compareTo(node.getData()) == 0) {
      return node.getData();
    }
    if (t.compareTo(node.getData()) < 0) {
      return getFromTree(t, node.getLeft());
    } else {
      return getFromTree(t, node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO
    if (t == null) {
      throw new NullPointerException();
    }
    return !(getFromTree(t, root) == null);
  }

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO
    if (t == null) {
      throw new NullPointerException();
    }
    return getFromTree(t, root);
  }

  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO
    if (isEmpty()) {
      return null;
    }
    return getMinimumHelper(root);
  }

  private T getMinimumHelper(BSTNode<T> root2) {
    // TODO Auto-generated method stub
    if (root2.getLeft() == null) {
      return root2.getData();
    } else {
      return getMinimumHelper(root2.getLeft());
    }
  }

  @Override
  public T getMaximum() {
    // TODO
    if (root == null) {
      return null;
    }
    return getHighestValue(root);
  }

  @Override
  public int height() {
    // TODO
    return heightHelper(root);
  }

  private int heightHelper(BSTNode<T> root2) {
    // TODO Auto-generated method stub
    if (root2 == null) {
      return -1;
    } else {
      return 1 + Math.max(heightHelper(root2.getLeft()), heightHelper(root2.getRight()));
    }
  }

  public Iterator<T> preorderIterator() {
    // TODO
    Queue<T> queue = new LinkedList<T>();
    preOrderTraverse(queue, root);
    return queue.iterator();
  }

  private void preOrderTraverse(Queue<T> queue, BSTNode<T> root2) {
    // TODO Auto-generated method stub
    if (root2 != null) {
      queue.add(root2.getData());
      preOrderTraverse(queue, root2.getLeft());
      preOrderTraverse(queue, root2.getRight());
    }
  }

  /**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }

  public Iterator<T> inorderHelper(BSTNode<T> node) {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, node);
    return queue.iterator();
  }

  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO
    Queue<T> queue = new LinkedList<T>();
    postOrderTraverse(queue, root);
    return queue.iterator();
  }

  private void postOrderTraverse(Queue<T> queue, BSTNode<T> root2) {
    // TODO Auto-generated method stub
    if (root2 != null) {
      postOrderTraverse(queue, root2.getLeft());
      postOrderTraverse(queue, root2.getRight());
      queue.add(root2.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO
    return equalsHelper(root, other.getRoot());
  }

  private boolean equalsHelper(BSTNode<T> first, BSTNode<T> second) {
    // TODO Auto-generated method stub
    if (first == null && second == null)
      return true;
    if (first == null || second == null)
      return false;
    T firstData = first.getData();
    T secondData = second.getData();
    if (firstData.compareTo(secondData) != 0) {
      return false;
    }
    return equalsHelper(first.getLeft(), second.getLeft()) && equalsHelper(first.getRight(), second.getRight());
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO
    Iterator<T> thisIter = this.inorderIterator();
    Iterator<T> otherIter = other.inorderIterator();
    while (thisIter.hasNext() && otherIter.hasNext()) {
      if (!(thisIter.next() == (otherIter.next()))) {
        return false;
      }
    }
    if (!thisIter.hasNext() && !otherIter.hasNext()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isBalanced() {
    // TODO
    double tempSize = (double) size();
    double min = Math.pow(2, height());
    double max = Math.pow(2, height() + 1);
    if (min <= tempSize && tempSize < max) {
      return true;
    }
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")

  public void balance() {
    // TODO
    T[] array = (T[]) new Comparable[size()];
    Iterator<T> iter = inorderIterator();
    int i = 0;
    while (iter.hasNext()) {
      array[i] = iter.next();
      i++;
    }
    root = null;
    balanceHelper(array, 0, array.length - 1);
  }

  private BSTNode<T> balanceHelper(T[] arr, int low, int high) {
    // TODO Auto-generated method stub
    if (low > high) {
      return null;
    }
    if (arr[low].compareTo(arr[high]) == 0) {
      add(arr[low]);
      return null;
    }
    if (arr[low + 1].compareTo(arr[high]) == 0) {
      add(arr[low]);
      add(arr[high]);
      return null;
    } else {
      int mid = (low + high) / 2;
      add(arr[mid]);
      balanceHelper(arr, low, (mid - 1));
      balanceHelper(arr, (mid + 1), high);
    }
    return root;
  }

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * 
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }

  /**
   * main method.
   * 
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}