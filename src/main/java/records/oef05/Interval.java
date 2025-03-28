package records.oef05;

public record Interval(long start, long end) {
    public Interval {
        if (start < 0) throw new IllegalArgumentException("start < 0");
        if (end < 0) throw new IllegalArgumentException("end < 0");
        if (start > end) throw new IllegalArgumentException("start cannot be after end");
    }

    public boolean overlapsWith(Interval other) {
        return this.start < other.end && this.end > other.start;
    }
}
