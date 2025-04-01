package collections.oef2_2;

public class MyLinkedList<E> {

    private class Node {
        public Node(E value) {
            this.value = value;
        }

        private Node next;
        private Node prev;
        private final E value;
    }

    private int size;
    private Node startSentinel;
    private Node endSentinel;

    public MyLinkedList() {
        this.size = 0;
        this.startSentinel = new Node(null);
        this.endSentinel = new Node(null);
        this.startSentinel.next = this.endSentinel;
        this.endSentinel.prev = this.startSentinel;
    }

    public int size() {
        return this.size;
    }

    public void add(E element) {
        var newNode = new Node(element);
        var prevLast = this.endSentinel.prev;
        // setup new node
        newNode.prev = prevLast;
        newNode.next = endSentinel;

        // setup previous node
        prevLast.next = newNode;

        // setup end sentinel
        endSentinel.prev = newNode;

        this.size++;
    }

    public E get(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        var current = this.startSentinel;
        while (index >= 0) {
            current = current.next;
            index--;
        }
        return current.value;
    }

    public void remove(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        var nodeToRemove = this.startSentinel;
        while (index >= 0) {
            nodeToRemove = nodeToRemove.next;
            index--;
        }
        var prev = nodeToRemove.prev;
        var next = nodeToRemove.next;
        prev.next = next;
        next.prev = prev;
        this.size--;
    }


}
