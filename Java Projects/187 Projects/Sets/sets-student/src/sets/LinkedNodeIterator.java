package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
  // TODO (1) define data variables
  private LinkedNode<E> current;

  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    // TODO (2) choose appropriate parameters and do the initialization
    current = head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
    return current != null;
  }

  @Override
  public E next() {
    // TODO (4)
    if (current == null) {
    throw new NoSuchElementException();
    }
    E temp = current.getData();
    current = current.getNext();
    return temp;
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
