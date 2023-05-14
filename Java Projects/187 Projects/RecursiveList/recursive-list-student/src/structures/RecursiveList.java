package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  class Node<T> {
    public T info;
    private Node<T> link;

    public Node() {
    }

    public Node(T info) {
      this.info = info;
    }

    public Node(T info, Node<T> l) {
      this.info = info;
      this.link = l;
    }
  }

  private int size;
  protected Node<T> head;

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }

  @Override
  public ListInterface<T> insertFirst(T elem) {
    // TODO Auto-generated method stub
    if (elem == null) {
      throw new NullPointerException();
    } else {
      Node<T> temp = new Node<T>(elem, head);
      head = temp;
      ++size;
      return this;
    }
  }

  @Override
  public ListInterface<T> insertLast(T elem) {
    // TODO Auto-generated method stub
    if (elem == null) {
      throw new NullPointerException();
    }
    return insertAt(size, elem);
  }

  @Override
  public ListInterface<T> insertAt(int index, T elem) {
    // TODO Auto-generated method stub
    if (index == 0) {
      return insertFirst(elem);
    } else if (elem == null) {
      throw new NullPointerException();
    } else if (index < 0 || size < index) {
      throw new IndexOutOfBoundsException();
    } else {
      Node<T> temp;
      temp = findNode(index - 1, 0, head);
      temp.link = new Node<T>(elem, findNode(index, 0, head));
      size++;
    }
    return this;
  }

  @Override
  public T removeFirst() {
    // TODO Auto-generated method stub
    if (head == null) {
      throw new IllegalStateException();
    }
    return removeAt(0);
  }

  @Override
  public T removeLast() {
    // TODO Auto-generated method stub
    if (head == null) {
      throw new IllegalStateException();
    }
    return removeAt(size - 1);
  }

  @Override
  public T removeAt(int i) {
    // TODO Auto-generated method stub
    Node<T> tempHead;
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (i == 0) {
      tempHead = head;
      head = head.link;
      size--;
    } else {
      Node<T> prevHead;
      prevHead = findNode(i - 1, 0, head);
      tempHead = prevHead.link;
      prevHead.link = prevHead.link.link;
      size--;
    }
    return tempHead.info;
  }

  @Override
  public T getFirst() {
    // TODO Auto-generated method stub
    if (head == null) {
      throw new IllegalStateException();
    }
    return get(0);
  }

  @Override
  public T getLast() {
    // TODO Auto-generated method stub
    if (head == null) {
      throw new IllegalStateException();
    }
    return get(size - 1);
  }

  @Override
  public T get(int i) {
    // TODO Auto-generated method stub
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    return findNode(i, 0, head).info;
  }

  @Override
  public boolean remove(T elem) {
    // TODO Auto-generated method stub
    if (elem == null) {
      throw new NullPointerException();
    }
    boolean check = true;
    int temp = indexOf(elem);
    if (temp == -1) {
      check = false;
    } else {
      removeAt(temp);
    }
    return check;
  }

  @Override
  public int indexOf(T elem) {
    // TODO Auto-generated method stub
    if (elem == null) {
      throw new NullPointerException();
    }
    return indexOfHelper(elem, head, 0);
  }

  public int indexOfHelper(T elem, Node<T> n, int index) {
    if (n == null) {
      return -1;
    }
    if (n.info == elem) {
      return index;
    } else {
      return indexOfHelper(elem, n.link, index + 1);
    }
  }

  public Node<T> findNode(int index, int count, Node<T> cur) {
    if (index == count) {
      return cur;
    } else {
      return findNode(index, count + 1, cur.link);
    }
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return head == null;
  }

}
