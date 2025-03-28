package records.oef06;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class RectangleTests {
    @Test
    public void remembers_corners() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.topLeft()).isEqualTo(topLeft);
        assertThat(rect.bottomRight()).isEqualTo(bottomRight);
    }

    @Test
    public void rejects_topLeft_toRightOf_bottomRight() {
        var topLeft = new Coordinate(4, 3);
        var bottomRight = new Coordinate(3, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> new Rectangle(topLeft, bottomRight));
    }

    @Test
    public void rejects_topLeft_below_bottomRight() {
        var topLeft = new Coordinate(1, 1);
        var bottomRight = new Coordinate(3, 4);
        assertThatIllegalArgumentException().isThrownBy(() -> new Rectangle(topLeft, bottomRight));
    }

    @Test
    public void returns_bottomLeft() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.bottomLeft()).isEqualTo(new Coordinate(1, 2));
    }

    @Test
    public void returns_topRight() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.topRight()).isEqualTo(new Coordinate(3, 4));
    }

    @Test
    public void contains_true_when_inside() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.contains(new Coordinate(2, 3))).isTrue();
    }

    @Test
    public void contains_false_when_outside_x() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.contains(new Coordinate(4, 3))).isFalse();
    }

    @Test
    public void contains_false_when_outside_y() {
        var topLeft = new Coordinate(1, 4);
        var bottomRight = new Coordinate(3, 2);
        var rect = new Rectangle(topLeft, bottomRight);
        assertThat(rect.contains(new Coordinate(2, 5))).isFalse();
    }

    @Test
    public void overlaps_with_self() {
        var rect = new Rectangle(new Coordinate(1, 4), new Coordinate(3, 2));
        assertThat(rect.overlapsWith(rect)).isTrue();
    }

    @Test
    public void overlaps_true() {
        var rect1 = new Rectangle(new Coordinate(1, 4), new Coordinate(3, 2));
        var rect2 = new Rectangle(new Coordinate(2, 3), new Coordinate(5, 0));
        assertThat(rect1.overlapsWith(rect2)).isTrue();
        assertThat(rect2.overlapsWith(rect1)).isTrue(); // symmetry
    }

    @Test
    public void overlaps_false() {
        var rect1 = new Rectangle(new Coordinate(1, 4), new Coordinate(3, 2));
        var rect2 = new Rectangle(new Coordinate(4, 3), new Coordinate(5, 0));
        assertThat(rect1.overlapsWith(rect2)).isFalse();
        assertThat(rect2.overlapsWith(rect1)).isFalse(); // symmetry
    }
}
