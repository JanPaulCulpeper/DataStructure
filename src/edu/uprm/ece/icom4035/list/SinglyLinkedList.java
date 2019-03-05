package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;




public class SinglyLinkedList<E> implements List<E> {

	private static class Node<E> { 
		private E element;
		private Node<E> next;

		public Node(E e, Node<E> n) {
			element = e; 
			next = n;
		}

		public E getElement() {return element; } 
		public Node<E> getNext() { return next; } 
		public void setNext(Node<E> n) { next = n; }
		public void setElement(E obj) {this.element = obj;}

	} 
	private Node<E> head; 
	private int size; 

	public SinglyLinkedList() {
		this.size = 0; 
		this.head = new Node<E>(null,null); // dummy head
	}
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	@Override
	public void add(E obj) {
		Node<E> newNode = new Node<E>(obj, null);// node to add
		if( this.head.getNext() == null) {
			head.setNext(newNode);
		}else {
			Node<E> dummy = head.getNext();//dummy head
			while( dummy.getNext() != null) {
				dummy = dummy.getNext();
			}
			dummy.setNext(newNode);
		}
		size++;
	}

	@Override
	public void add(int index, E obj) {
		Node<E> newNode = new Node<E>(obj, null); //node to add
		if( index == 0) {
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			this.size++;
			return;
		}
		int count = 1;
		Node<E> dummy = head.getNext();//dummy
		while( count != index) {
			count++;
			dummy = dummy.getNext();	
		}
		if( index == this.size) {
			dummy.setNext(newNode);
		}else {
			Node<E> BeforeIndNode = dummy.getNext();
			newNode.setNext(BeforeIndNode);
			dummy.setNext(newNode);
		}
		this.size++;
	}

	@Override
	public boolean remove(E obj) {
		Node<E> dummy = head.getNext();//dummy that will run the list looking for the obj
		if(dummy.getElement() == obj) {
			remove(0);
			return true;
		}
		while(dummy.getNext() != null ) { 
			if( dummy.getElement().equals(obj)) {
				Node<E> before = head.getNext(); 
				while(before.getNext() != dummy) {
					before = before.getNext();
				}
				before.setNext(dummy.getNext());
				size--;
				return true;
			}
			dummy = dummy.getNext();
		}
		size--;

		return false;
	}

	@Override
	public boolean remove(int index) throws IndexOutOfBoundsException {
		if( index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		Node<E> dummy= head.getNext();//dummy that will run the list looking for the obj
		int count = 0; 
		if(index==0) {
			head.setNext(dummy.getNext());
			size--;
			return true;
		}
		while(count != index) { 
			dummy = dummy.getNext();
			count++;
		}
		Node<E> before = head.getNext(); 
		while(before.getNext() != dummy) {
			before = before.getNext();
		}
		before.setNext(dummy.getNext());
		size--;
		return true;
	}
	@Override
	public int removeAll(E obj) {
		int count = 0; 
		int index=0; //guarda el indece para saber que node borrar
		Node<E> dummy = head.getNext();//dummy that will run the list looking for the obj
		if(dummy.getElement() == obj) {
			remove(0);
			count++;
		}
		while(dummy.getNext() != null) { 
			if( dummy.getElement().equals(obj)) {
				remove(index);
				count++;
				index--;
			}
			dummy = dummy.getNext();
			index++;
		}
		if(index==size) {
			if(dummy.getElement().equals(obj)) {
				remove(index);
				count++;
				index--;
			}
		}
		return count;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if( index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		Node<E> dummy = head.getNext();//dummy that will run the list 
		int count = 0; 
		while(count != index) { 
			count++;
			dummy = dummy.getNext();	
		}
		return dummy.getElement();
	}

	@Override
	public E set(int index, E obj) throws IndexOutOfBoundsException {
		if( index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		Node<E> dummy = head.getNext();//dummy that will run the list 
		int count = 0; 
		while(count != index) { 
			count++;
			dummy = dummy.getNext();	
		}
		E res = dummy.getElement();
		dummy.setElement(obj);
		return res;
	}

	@Override
	public E first() {
		if( this.isEmpty()) {
			return null;
		}else {
			return this.head.getNext().getElement();
		}
	}
	@Override
	public E last() {
		if( this.isEmpty()) {
			return null;
		}else {
			Node<E> dummy = head.getNext();//dummy that will run the list looking for the last node
			while(dummy.getNext() != null) {
				dummy = dummy.getNext();
			}
			return dummy.getElement();
		}
	}

	@Override
	public int firstIndex(E obj) {
		Node<E> dummy = head.getNext();//dummy that will run the list looking for the obj
		int count = 0;
		while(dummy.getNext() != null) {
			if( dummy.getElement().equals(obj)) {
				return count;
			}else {
				count++;
				dummy = dummy.getNext();
			}
		}if(dummy.getNext()==null) {
			if( dummy.getElement().equals(obj)) {
				return count;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		Node<E> dummy = head.getNext();//dummy that will run the list looking for the obj
		int lIndex = -1; // last index
		for (int i = 0; i < size; i++) {
			if (dummy.getElement().equals(obj)) {
				lIndex = i;
			}
			dummy= dummy.getNext();
		}
		return lIndex;	
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if( head.getNext() == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(E obj) {
		Node<E> dummy = head.getNext();//dummy that will run the list looking for the obj
		while(dummy.getNext() != null) { 
			if( dummy.getElement().equals(obj)) {
				return true;
			}
			dummy = dummy.getNext();
		}
		return false;
	}

	@Override
	public void clear() {
		Node<E> dummy = head.getNext();//dummy that will run the 
		while(dummy.getNext() != null) { // sets to null every element 
			dummy.setElement(null);
			dummy = dummy.getNext();
			this.size--;
		}
	}
	private class SinglyLinkedListIterator implements Iterator<E> {
		private Node<E> current;
		public SinglyLinkedListIterator() {
			current = (Node<E>) head.getNext();
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if(current == null) 
				throw new NoSuchElementException();
			E temp = current.getElement();
			current = current.getNext();
			return temp;
		}


	}
}
