package collections.oef3_1;

public class MyFIFO<E> {

    private final E[] elements;
    private int size;
    private int nextIn;
    private int nextOut;

    @SuppressWarnings("unchecked")
    public MyFIFO(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        this.elements = (E[]) new Object[capacity];
        this.nextIn = 0;
        this.nextOut = 0;
        this.size = 0;
    }
    
    public int size() {
        return this.size;
    }

    public E poll() {
        if (size() == 0) return null;
        var result = elements[nextOut++];
        if (nextOut == elements.length) nextOut = 0;
        this.size--;
        return result;
    }

    public boolean add(E e) {
        if (size() >= elements.length) return false;
        elements[nextIn++] = e;
        if (nextIn == elements.length) nextIn = 0;
        this.size++;
        return true;
    }
}
