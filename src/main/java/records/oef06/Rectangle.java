package records.oef06;

public record Rectangle(Coordinate topLeft, Coordinate bottomRight) {
    public Rectangle {
        if (topLeft.isRightOf(bottomRight)) throw new IllegalArgumentException();
        if (topLeft.isBelow(bottomRight)) throw new IllegalArgumentException();
    }

    public Coordinate bottomLeft() {
        return new Coordinate(topLeft().x(), bottomRight().y());
    }

    public Coordinate topRight() {
        return new Coordinate(bottomRight().x(), topLeft().y());
    }

    public boolean contains(Coordinate point) {
        return topLeft().x() <= point.x() && point.x() <= topRight().x() &&
                bottomRight().y() <= point.y() && point.y() <= topRight().y();
    }

    public boolean overlapsWith(Rectangle other) {
        return this.contains(other.topLeft()) || other.contains(this.topLeft()) ||
                this.contains(other.topRight()) || other.contains(this.topRight()) ||
                this.contains(other.bottomLeft()) || other.contains(this.bottomLeft()) ||
                this.contains(other.bottomRight()) || other.contains(this.bottomRight());
    }
}
