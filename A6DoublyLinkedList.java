package assignment06;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import components.list.DoublyLinkedList;

/**
 * @author Jonathan Oliveros and Joshua Wells
 *	Generic double linked list where each element links to the previous one 
 *	and the next one. Additionally, the start and end nodes which contain no
 *	data are circular in that they are connected as each others next and previous
 * @param <E>
 */
public class A6DoublyLinkedList<E> extends DoublyLinkedList<E> {
	
	/**
	 * @author Jonathan Oliveros and Joshua Wells
	 *	Doubly linked node with a previous and next
	 *	linked node
	 * @param <E>
	 */
	private static class Node<E> { 
		Node<E> next, previous; 
		E data; 
	}
	

    /**
	 * @author Jonathan Oliveros and Joshua Wells
	 *	An iterator for this class
	 */
	private class DoublyLinkedListIterator implements Iterator<E> {
		/*** Current  node.*/
		private  Node <E> current;
		
		/*** Expected  count of  modifications , set to  modCount  in the  constructor.*/
		private  int  expectedModCount;
		  
		/**
		 * no argument constructor
		 */
		DoublyLinkedListIterator () {
			current = start.next;
			expectedModCount = modCount;
		}

		/**
		 * this is an overload of the {@code next} method which did not allow 
		 * modifications to the linked list while it was iterating. This method
		 * does allow modification while it iterates however.
		 * @param modified is a string that allows the overloading of this method
		 * @return null iff the current node is end or the data of the current node
		 */
		private E next(String modified) {
			if(current != end) {
				E hold = current.data;
				current = current.next;
				return  hold;
			}
			return end.data;
		}
		
		@Override
		public boolean hasNext() {
			if (expectedModCount  !=  modCount)
				throw  new  ConcurrentModificationException ();
			return current != end;
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
	 * size is the length of the list, modcount is an integer which grows for every action that modifies the list
	 */
	private int size, modCount;
	
	/**
	 * start and end nodes for organizing the list
	 */
	private Node<E> start, end;
	
	  /**
	 * no argument constructor creates a circular doulby linked list
	 */
	public A6DoublyLinkedList() {
			super();
			clear();
		}
		
		@Override
		public void clear() {
		    start = new Node<>();
		    end = new Node<>();
		    
		    //make the list circular
		    start.next = end;
			end.next = start;
			start.previous = end;
			end.previous = start;
		    
		    size = 0;

		    modCount++;
		  }
		
		@Override
		public void add(E x) {
			Node<E> n = new Node<>();
			n.data = x;
			Node<E> p = new Node<>();
			p = this.end.previous;
			p.next = n;
			end.previous = n;
			n.next = end;
			n.previous = p;
			size++;
			modCount++;
		}
		
		@Override
		public void add(int index, E x) {
			Node<E> n = new Node<>();
			Node<E> e = new Node<>();
			Node<E> p = new Node<>();
			p = this.start;
			
			n.data = x;
			
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			e = p.next;
			e.previous = n;
			n.next = p.next;
			n.previous = p;
			p.next = n;
			
			size++;
			modCount++;
		}
		
		/* 
		 * sets the data stored in the node at postion index to {@ code x}
		 */
		public void set(int index, E x) {
			assert 0 <= index : "Violation of: index is non-negative";
			assert index <= this.size() : "Violation of: index is less than size()";
			assert this.size() > 0 : "Position does not exist yet";
			Node<E> p = start.next;
			//find the node at index
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			//set its data to x
			p.data = x;
			modCount++;
		}
		
		@Override
		public E remove(int index) {
			Node<E> front = this.start;
			//find the node just before index
			for (int i = 0; i < index; i++)
			      front = front.next;
			//record the data in the node to be removed
			E data = front.next.data;
			//assign all pointers to skip this item, derefrencing it
		    Node<E> hold = front.next;
		    front.next = hold.next;
		    hold = hold.next;
		    hold.previous = start;
		    size--;
		    modCount++;
		    //return the data from the node that was removed
		    return data;
		}
		
		@Override
		public int indexOf(E x) {
			int index = 0;
			DoublyLinkedListIterator it = new DoublyLinkedListIterator();
			while(it.hasNext()) {
				//E data = get(index);
				if(it.next().equals(x)) {
					return index;
				}
				index++;
			}
			//if x is not stored in the list
			return -1;
		}
		
		@Override
		public int size() {
			return this.size;
		}
		
		@Override
		public E get(int index) {
			assert 0 <= index : "Violation of: index is non-negative";
			assert index <= this.size() : "Violation of: index is less than size()";
			int i = 0;
			E element = null;
			DoublyLinkedListIterator it = new DoublyLinkedListIterator();
			while (it.hasNext() && i <= index) {
				element = it.next();
				i++;
			}
			return element;
		}
	
	  @Override
	  public DoublyLinkedListIterator iterator() {
		  DoublyLinkedListIterator it = new DoublyLinkedListIterator();
		  return it;
	  }
	
	  @Override
		public void reverse() {
			DoublyLinkedListIterator it = this.iterator();
			int iteration = 0;
			E firstData = start.data;
			E lastData = end.data;
			int lastIndex = size() - 1;
			//for half of the list
			while(size() / 2 > iteration) {
				firstData = it.next("modified");
				lastData = get(lastIndex - iteration);
				//flip each item at each end
				set(iteration, lastData);
				set(lastIndex - iteration, firstData);
				//increment towards the middle of the list
				iteration++;
			}
			modCount++;
		}
	
	@Override
	public String toString() {
		  StringBuilder output = new StringBuilder();
		  output.append("[");
		  Iterator<E> it = this.iterator();
		  while(it.hasNext()) {
			  output.append(it.next());
			  if(it.hasNext())
				  output.append(",");
		  }
		  output.append("]");
		  return output.toString();
	  }
}