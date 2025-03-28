package records.oef06;

public record Coordinate(double x, double y) {
    public boolean isRightOf(Coordinate other) {
        return this.x > other.x;
    }

    public boolean isBelow(Coordinate other) {
        return this.y < other.y;
    }
}
