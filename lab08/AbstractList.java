package lab08;

import java.util.Iterator;

/**
 * Layered implementation of the {@code List} methods.
 * 
 * @author Swaroop Joshi
 *
 * @param <E> type of the elements of the list
 */
public abstract class AbstractList<E> implements List<E> {
  /*
   * Methods inherited from java.lang.Object
   */
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (this == o)
      return true;
    if (!(o instanceof List))
      return false;
    List<?> that = (List<?>) o;
    if (this.size() != that.size())
      return false;

    Iterator<E> thisIter = this.iterator();
    Iterator<?> thatIter = that.iterator();
    while (thisIter.hasNext()) {
      E thisElement = thisIter.next();
      Object thatElement = thatIter.next();
      if (!thisElement.equals(thatElement))
        return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int samples = 2;
    final int a = 37;
    final int b = 17;

    int h = 0;

    int counter = 0;
    Iterator<E> it = this.iterator();
    while (counter < samples && it.hasNext()) {
      E element = it.next();
      h = a * h + b * element.hashCode();
      counter++;
    }
    return h;
  }

  /*
   * Methods from List interface
   */
  @Override
  public void add(E x) {
    assert x != null : "Violation of: x is not null";

    this.add(this.size(), x);
  }

  @Override
  public void set(int index, E x) {
    assert 0 <= index : "Violation of: 0 <= index";
    assert index < this.size() : "Violation of: index < this.size()";
    assert x != null : "Violation of: x is not null";

    this.remove(index);
    this.add(index, x);
  }

  @Override
  public void reverse() {
    if (this.size() > 1) {
      E first = this.remove(0);
      this.reverse();
      this.add(first);
    }
  }
}
