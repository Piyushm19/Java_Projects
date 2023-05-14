package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
    int count = 0;
    LinkedNode<E> tempHead;
    tempHead = head;
    while (tempHead != null) {
      count++;
      tempHead = tempHead.getNext();
    }
    return count;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
    LinkedNode<E> tempNode;
    tempNode = head;   
    while (tempNode != null) {
      if(tempNode.getData().equals(o)) {
        return true;
      } else {
        tempNode = tempNode.getNext();
      }      
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
    for(E item: this) {
      if(!that.contains(item)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
    LinkedNode<E>tempHead;
    if(this.contains(e)) {
      return this;
    } else {
     tempHead = new LinkedNode<E>(e, head);    
    }  
    return new LinkedSet<E>(tempHead);
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
    LinkedNode<E> nodePtr = head;
    for(E target : that) {
      if(!this.contains(target)) {
        LinkedNode<E> tempNode = new LinkedNode<E>(target, nodePtr);
        nodePtr = tempNode;
      }
    }
    return new LinkedSet<E>(nodePtr);
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
    LinkedNode<E> nodePtr = null;
    for(E target : this) {
      if(that.contains(target)) {
        LinkedNode<E> tempNode = new LinkedNode<E>(target, nodePtr);
        nodePtr = tempNode;
      }
    }
    return new LinkedSet<E>(nodePtr);
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
    LinkedNode<E> nodePtr = null;
    for(E target : this) {
      if(!that.contains(target)) {
        LinkedNode<E> tempNode = new LinkedNode<E>(target, nodePtr);
        nodePtr = tempNode;
      }
    }
    return new LinkedSet<E>(nodePtr);
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
    LinkedNode<E> nodePtr = null;
    for(E target : this) {
      if (target != (e)) {
        LinkedNode<E> tempNode = new LinkedNode<E>(target, nodePtr);
        nodePtr = tempNode;
      }
    }
    return new LinkedSet<E>(nodePtr);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
