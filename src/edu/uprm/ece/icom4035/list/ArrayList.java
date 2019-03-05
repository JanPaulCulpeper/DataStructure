package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

	private static final int INITCAP=2;
	private static final int CAPTOAR = 1;
	private static final int MAXEMPTYPOS = 2;
	private E element[];
	private int size;

	public ArrayList() {
		element =(E[]) new Object[INITCAP];
		size=0;
	}

	@Override
	public Iterator<E> iterator() {return new ArrayListIterator();}

	@Override
	public void add(E obj) {
		if(element.length == size) {
			changeCapacity(CAPTOAR);
		}
		element[size]=obj;
		size++;
	}

	@Override
	public void add(int index, E obj)  throws IndexOutOfBoundsException {
		if ((index < 0) || (index > this.size())) {
			throw new IndexOutOfBoundsException();
		}
		if (this.size() == this.element.length) {
			this.changeCapacity(2 * this.size());
		}
		if (index == this.size()) {
			this.add(obj);
			return;
		}
		for (int i= this.size(); i > index ; --i) {
			this.element[i] = this.element[i-1];
		}
		this.element[index] = obj;
		this.size++;

	}

	@Override
	public boolean remove(E obj) {
		int index = -1; 
		for( int i = 0; i<this.size; i++) { 
			if(this.element[i].equals(obj)) {//iterando por la lista hasta encontrar un obj igual
				index = i; 
				break;
			}
		}
		this.element[index] = null;
		this.moveDataOnePositionTL(index + 1, this.size -1);
		size--;

		if( this.element.length - this.size > MAXEMPTYPOS) {//verifica si hay que cambiar el tamaño
			this.changeCapacity(-CAPTOAR);
		}
		if( index != -1) {
			return true;
		}
		return false; 
	}

	@Override
	public boolean remove(int index) throws IndexOutOfBoundsException {
		if( index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("remove: Invalid inddex = " + index);
		}
		moveDataOnePositionTL(index+1, size-1);
		size--;
		element[size]=null;             
		if(element.length-size == MAXEMPTYPOS) {//verifica si hay que cambiar el tamaño
			changeCapacity(-CAPTOAR);
		}
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int i=0;//para comparar tamaño
		int num=0;//guarda cuantos obj hay en la lista para luego devolverlo
		while(i<this.size) {
			if(this.element[i].equals(obj)) {
				this.remove(i);
				num++;
			}
			i++;
		}
		return num;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if ((index < 0 ) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		}
		return this.element[index];
	}

	@Override
	public E set(int index, E obj) throws IndexOutOfBoundsException {
		if ((index < 0 ) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		}
		E result = this.element[index];
		this.element[index] = obj;
		return result;
	}

	@Override
	public E first() {
		if(this.isEmpty()) {
			return null;
		}else {
			return this.element[0];
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()) {
			return null;
		}else {
			return this.element[size-1];
		}
	}

	@Override
	public int firstIndex(E obj) {
		int i=0;//guarda el indice donde se encuetra el obj
		while(i<this.size-1) {
			if(this.element[i].equals(obj)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		int i=size-1;//guarda el ultimo espacio donde hay algo para iterar en reversa
		while(i>0) {
			if(this.element[i].equals(obj)) {
				return i;
			}
			i--;
		}
		return -1;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public boolean contains(E obj) {
		int i=0;//index para comparar el size
		while(i<size) {
			if(this.element[i].equals(obj)) {
				return true;
			}
			i++;
		}
		return false;
	}

	@Override
	public void clear() {
		while(this.size!=0) {
			this.remove(first());
		}

	}
	//agranda y achica el array dependiendo de cuantos espacios quedan
	private void changeCapacity(int change) {
		int newCapacity = element.length + change;
		E[] newElement = (E[]) new Object[newCapacity];
		for (int i=0; i<size; i++) {
			newElement[i] = element[i];
			element[i] = null;
		}
		element = newElement;
	}

	//se utiliza para mover los elementos del array luego de borrar uno
	private void moveDataOnePositionTL(int low, int sup) {
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos];
	}
	private class ArrayListIterator implements Iterator<E> {

		private int index=0;

		@Override
		public boolean hasNext() {
			return index <size;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Iterator is complete");
			}
			return element[index++];
		}

	}
}
