package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check
 * support/structures/UnboundedQueueInterface.java for detailed explanation of
 * each interface method, including the parameters, return values, assumptions,
 * and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

  class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
      this.data = data;
    }

    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public Queue() {
    // TODO 1
  }

  public Queue(Queue<T> other) {
    // TODO 2
    Node<T> tempNode = other.head;
    while (tempNode != null) {
      this.enqueue(tempNode.data);
      tempNode = tempNode.next;
    }
  }

  @Override
  public boolean isEmpty() {
    // TODO 3
    return size == 0;
  }

  @Override
  public int size() {
    // TODO 4
    return size;
  }

  @Override
  public void enqueue(T element) {
    // TODO 5
    ++size;
    if (head == null) {
      Node<T> temp = new Node<T>(element);
      head = temp;
      tail = head;
    } else {
      Node<T> temp = new Node<T>(element);
      tail.next = temp;
      tail = temp;
    }

  }

  @Override
  public T dequeue() throws NoSuchElementException {
    // TODO 6
    Node<T> temp;
    if (isEmpty()) {
      throw new NoSuchElementException();
    } else {
      --size;
      temp = head;
      head = head.next;
    }
    return temp.data;
  }

  @Override
  public T peek() throws NoSuchElementException {
    // TODO 7
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return head.data;
  }

  @Override
  public UnboundedQueueInterface<T> reversed() {
    // TODO 8
    Queue<T> tempQ = new Queue<T>();
    helperMethod(head, tempQ);
    return tempQ;
  }

  public void helperMethod(Node<T> n, Queue<T> q) {
    if (n == null) {
      return;
    } else {
      helperMethod(n.next, q);
      q.enqueue(n.data);
    }
  }
}
