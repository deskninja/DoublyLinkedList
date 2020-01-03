package lab08;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list implementation of {@code List} with one smart node.
 *
 * @author Swaroop Joshi
 *
 * @param <E> type of elements of the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {
  /*
   * Private members
   */
	
  private static final int NOT_FOUND = -1;

  private static class Node<E> {
    public Node() {
      next = null;
    }

    private E data;
    private Node<E> next;
  }
  
  private class SinglyLinkedListIterator implements Iterator<E>{
	  /*** Current  node.*/
	  private  Node <E> current;
	  /*** Expected  count of  modifications , set to  modCount  in the  constructor.*/
	  private  int  expectedModCount;
	  
	  SinglyLinkedListIterator () {
		  current = head.next;
		  expectedModCount = modCount;
	  }

	@Override
	public boolean hasNext() {
		if (expectedModCount  !=  modCount)
			throw  new  ConcurrentModificationException ();
		return current != null;
	}

	@Override
	public E next() {
		if (!hasNext ())
			throw  new  NoSuchElementException ();
		E hold = current.data;
		current = current.next;
		return  hold;
	}
	
	//because this is a read only iterator implementation, the remove method throws an exception
	@Override
	public void remove() {
		throw  new  UnsupportedOperationException(); //does not allow this method to be called 
	}
  }

  /**
   * Head node of the singly-linked list.
   */
  private Node<E> head;
  /**
   * Holds the size of {@code this}.
   */
  private int size;
  /**
   * Hold the number of modifications made to the list so far, useful for
   * iterator.
   */
  private int modCount = 0;

  /*
   * Constructors
   */
  /**
   * No argument constructor.
   */
  public SinglyLinkedList() {
    clear();
  }

  @Override
  public void clear() {
    head = new Node<>();
    size = 0;

    modCount++;
  }

  @Override
  public void add(int index, E x) {
    assert 0 <= index : "Violation of: 0 <= index";
    assert index <= this.size() : "Violation of: index < this.size()";
    assert x != null : "Violation of: x is not null";

    Node<E> n = new Node<>();
    n.data = x;

    Node<E> p = head;
    for (int i = 0; i < index; i++)
      p = p.next;

    n.next = p.next;
    p.next = n;

    size++;
    modCount++;
  }

  @Override
  public E remove(int index) {
    assert 0 <= index : "Violation of: 0 <= index";
    assert index < this.size() : "Violation of: index < this.size()";

    Node<E> p = head;
    for (int i = 0; i < index; i++)
      p = p.next;
    Node<E> hold = p.next;
    p.next = hold.next;

    size--;
    modCount++;
    return hold.data;
  }

  @Override
  public int indexOf(E x) {
    assert x != null : "Violation of: x is not null";

    Node<E> p = head.next;
    int result = NOT_FOUND;
    int i = 0;
    while (p != null) {
      if (p.data.equals(x)) {
        result = i;
        break;
      }
      i++;
      p = p.next;
    }
    return result;
  }

  @Override
  public int size() {
    return size;
  }

  /*
   * Secondary methods
   */
  @Override
  public E get(int index) {
    assert 0 <= index : "Violation of: 0 <= index";
    assert index < this.size() : "Violation of: index < this.size()";

    Node<E> p = head.next;
    for (int i = 0; i < index; i++)
      p = p.next;

    return p.data;
  }

  /*
   * Iterator
   */
  @Override
  public Iterator<E> iterator() {
	  SinglyLinkedListIterator it = new SinglyLinkedListIterator();
	  return it;
  }

  /*
   * Methods inherited from Object class
   */
  @Override
  public String toString() {
	  StringBuilder output = new StringBuilder();
	  Iterator<E> it = this.iterator();
	  while(it.hasNext()) {
		  output.append(it.next());
	  }
	  return output.toString();
  }

}
