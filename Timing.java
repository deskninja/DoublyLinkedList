package assignment06;

import java.util.Random;

import components.list.ListOnArrays;

public class Timing {

	private static Random rand = new Random();

	public static A6DoublyLinkedList<Integer> createList(int size){
		A6DoublyLinkedList<Integer> test = new A6DoublyLinkedList<>();
		for (int i = 0; i < size - 1; i++) {
			test.add(rand.nextInt(50));
		}
		return test;
	}
	
	public static long testAndTimeAdd(int size, int position) {

		A6DoublyLinkedList<Integer> test = createList(size);
		
		long start = System.nanoTime();
		test.add(position, rand.nextInt(50));
		long stop = System.nanoTime();
		long time = stop - start;
		return time;
	}
	
	public static long testAndTimeRemove(int size, int position) {

		A6DoublyLinkedList<Integer> test = createList(size);
		
		long start = System.nanoTime();
		test.remove(position);
		long stop = System.nanoTime();
		long time = stop - start;
		return time;
	}
	
	public static ListOnArrays<Integer> createCollectionList(int size){
		ListOnArrays<Integer> test = new ListOnArrays<>();
		for (int i = 0; i < size - 1; i++) {
			test.add(rand.nextInt(50));
		}
		return test;
	}
	
	public static long testAndTimeAddCollection(int size, int position) {

		ListOnArrays<Integer> test = createCollectionList(size);
		
		long start = System.nanoTime();
		test.add(position, rand.nextInt(50));
		long stop = System.nanoTime();
		long time = stop - start;
		return time;
	}
	
	public static long testAndTimeRemoveCollection(int size, int position) {

		ListOnArrays<Integer> test = createCollectionList(size);
		
		long start = System.nanoTime();
		test.remove(position);
		long stop = System.nanoTime();
		long time = stop - start;
		return time;
	}
	
	public static StringBuilder outputTestResultsAdd(int start, int limit, int index, String testType) {
		StringBuilder output = new StringBuilder();
		output.append("\n");
		output.append(testType);
		output.append("\n");
		output.append("Size\ttime\n");
		output.append("-----------------------------");
		for(int i = start; i < limit; i += 1_000) {
			output.append(i);
			output.append("\t");
			if(index == 0)
				output.append(testAndTimeRemove(i, 0));
			else if(index == 1)
				output.append(testAndTimeRemove(i, i/2));
			else 
				output.append(testAndTimeRemove(0, i - 1));
			output.append("\n");
		}
		return output;
	}
	
	public static StringBuilder outputTestResultsRemove(int start, int limit, int index, String testType) {
		StringBuilder output = new StringBuilder();
		output.append("\n");
		output.append(testType);
		output.append("\n");
		output.append("Size\ttime\n");
		output.append("-----------------------------");
		for(int i = start; i < limit; i += 1_000) {
			output.append(i);
			output.append("\t");
			if(index == 0)
				output.append(testAndTimeRemove(i, 0));
			else if(index == 1)
				output.append(testAndTimeRemove(i, i/2));
			else 
				output.append(testAndTimeRemove(0, i - 1));
			output.append("\n");
		}
		return output;
	}

	public static StringBuilder outputTestResultsAddCollection(int start, int limit, int index, String testType) {
		StringBuilder output = new StringBuilder();
		output.append("\n");
		output.append(testType);
		output.append("\n");
		output.append("Size\ttime\n");
		output.append("-----------------------------");
		for(int i = start; i < limit; i += 1_000) {
			output.append(i);
			output.append("\t");
			if(index == 0)
				output.append(testAndTimeRemove(i, 0));
			else if(index == 1)
				output.append(testAndTimeRemove(i, i/2));
			else 
				output.append(testAndTimeRemove(0, i - 1));
			output.append("\n");
		}
		return output;
	}
	
	public static StringBuilder outputTestResultsRemoveCollection(int start, int limit, int index, String testType) {
		StringBuilder output = new StringBuilder();
		output.append("\n");
		output.append(testType);
		output.append("\n");
		output.append("Size\ttime\n");
		output.append("-----------------------------");
		for(int i = start; i < limit; i += 1_000) {
			output.append(i);
			output.append("\t");
			if(index == 0)
				output.append(testAndTimeRemove(i, 0));
			else if(index == 1)
				output.append(testAndTimeRemove(i, i/2));
			else 
				output.append(testAndTimeRemove(0, i - 1));
			output.append("\n");
		}
		return output;
	}
	
	
	public static void main(String[] args) {
		StringBuilder output = new StringBuilder();
		int limit = 1_000_000;
		final int START = 0;
		final int MIDDLE = 1;
		final int END = 2;
		//doubly linked list tests
		output.append("Linked List\n");
		//adding methods
			//add at the beginning
		output.append(outputTestResultsAdd(10, limit, START, "add at the beginning"));
			//add at the middle
		output.append(outputTestResultsAdd(10, limit, MIDDLE, "add at the middle"));
			//add at the end
		output.append(outputTestResultsAdd(10, limit, END, "add at the end"));
		//removing methods
			//remove at the beginning 
		output.append(outputTestResultsRemove(10, limit, START, "remove at the beginning"));
			//remove at the middle
		output.append(outputTestResultsRemove(10, limit, MIDDLE, "remove at the middle"));
			//remove at the end
		output.append(outputTestResultsRemove(10, limit, END, "remove at the end"));
		
		output.append("\n");
		output.append("ListOnArrays");
		//list on arrays tests
		//adding methods
			//add at the beginning
		output.append(outputTestResultsAddCollection(10, limit, START, "add at the beginning"));
			//add at the middle
		output.append(outputTestResultsAddCollection(10, limit, MIDDLE, "add at the middle"));
			//add at the end
		output.append(outputTestResultsAddCollection(10, limit, END, "add at the end"));
		//removing methods
			//remove at the beginning 
		output.append(outputTestResultsAddCollection(10, limit, START, "remove at the beginning"));
			//remove at the middle
		output.append(outputTestResultsAddCollection(10, limit, MIDDLE, "remove at the middle"));
			//remove at the end
		output.append(outputTestResultsAddCollection(10, limit, END, "remove at the end"));
		//output to console
		System.out.println(output);
	}
}