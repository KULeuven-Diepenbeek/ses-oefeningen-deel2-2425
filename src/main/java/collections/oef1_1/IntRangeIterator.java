package collections.oef1_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntRangeIterator implements Iterator<Integer> {
    private final int start, stop;
    private int current;

    public IntRangeIterator(int start, int stop) {
        this.start = start;
        this.stop = stop;
        this.current = start;
    }

    @Override
    public boolean hasNext() {
        return current <= stop;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return current++;
    }
}
