package collections.oef1_1;

import java.util.Iterator;

public record IntRange(int start, int stop) implements Iterable<Integer> {
    public IntRange {
        if (start > stop) throw new IllegalArgumentException();
    }


    @Override
    public Iterator<Integer> iterator() {
        return new IntRangeIterator(start, stop);
    }
}
