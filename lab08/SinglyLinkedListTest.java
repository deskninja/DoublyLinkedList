package lab08;

import static org.junit.Assert.*;

import org.junit.Test;

public class SinglyLinkedListTest {

	@Test
	public void toStringTest() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		list.add(5);
		list.add(7);
		list.add(12);
		list.add(1);
		System.out.println(list.toString());
	}

}
