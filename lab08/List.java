package lab08;

/**
 * {@code List} interface with primary and secondary methods.
 * 
 * @author Swaroop Joshi
 *
 * @param <E> type of the elements of the List
 */
public interface List<E> extends Iterable<E> {
  /*
   * Primary methods -----
   */
  /**
   * Adds the given element at the given index in this list.
   * 
   * @param index position to add x at
   * @param x     element to be added
   * @requires 0 <= index <= this.size()
   * @modifies {@code this}
   */
  public void add(int index, E x);

  /**
   * Removes and returns the element at the given index from this list.
   * 
   * @param index position from which to remove the element
   * @return element at {@code index}
   * @requires 0 <= index < this.size()
   * @modifies {@code this}
   */
  public E remove(int index);

  /**
   * Returns the index of the first occurrence of the specified element in this
   * list, or -1 if this list does not contain the element.
   * 
   * @param x element to look for
   * @return index of the first occurrence of {@code x}, -1 if it is not found in
   *         {@code this}.
   */
  public int indexOf(E x);

  /**
   * Returns the number of elements in this list.
   *
   * @return the number of elements in this list
   */
  public int size();

  /**
   * Removes all of the elements from this list.
   * 
   * @modifies {@code this}
   */
  public void clear();

  /*
   * Secondary methods. Implementation can be provided in a layered abstract
   * class. Some secondary methods can be overridden in the concrete
   * implementation classes to take advantage of the underlying representation.
   */
  /**
   * Adds {@code x} at the end of this list.
   * 
   * @param x element to be added
   */
  public void add(E x);

  /**
   * Returns the element at the given index from this list.
   * 
   * @param index index of the element to return
   * @return element at {@code index}
   * @requires 0 <= index < this.size()
   */
  public E get(int index);

  /**
   * Replaces the element at the specified position in this list with the
   * specified element.
   * 
   * @param index index of the element to replace
   * @param x     element to be stored at the specified position
   * 
   * @requires 0 <= index < this.size()
   * @modifies {@code this}
   */
  public void set(int index, E x);

  /**
   * Reverses the order of elements in this list.
   * 
   * @modifies {@code this}
   */
  public void reverse();
}
