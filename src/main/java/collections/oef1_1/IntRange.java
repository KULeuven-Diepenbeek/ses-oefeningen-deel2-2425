package collections.oef1_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

class IntRangeIterator
        implements Iterator<Integer>
{

    private final int lowest, highest;
    private int next;

    public IntRangeIterator(
            int lowest,
            int highest) {
        this.lowest = lowest;
        this.highest = highest;
        this.next = lowest;
    }
    @Override
    public boolean hasNext() {
        return next <= highest;
    }
    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return next++;
    }
}


public record IntRange(
        int lowest,
        int highest
) implements Iterable<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new IntRangeIterator(
                lowest, highest);
    }

    public static void main(String[] args) {
        IntRange range = new IntRange(3, 6);
        for (int x : range) {
            System.out.println(x);
        }
    }
}
