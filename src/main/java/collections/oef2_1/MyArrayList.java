package collections.oef2_1;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> {
    private E[] elements;
    private int size;

    public MyArrayList() {
        this.elements = (E[]) new Object[4];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    public void add(E element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        elements[size++] = element;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    public E last() {
        if (size == 0) throw new NoSuchElementException();
        return elements[size - 1];
    }
}
