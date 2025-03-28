package records.oef05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class IntervalTests {

    @Test
    public void stores_start_end() {
        var interval = new Interval(3, 5);
        assertThat(interval.start()).isEqualTo(3);
        assertThat(interval.end()).isEqualTo(5);
    }

    @Test
    public void rejects_negative_start() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Interval(-1, 100));
    }

    @Test
    public void rejects_negative_end() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Interval(10, -1));
    }

    @Test
    public void rejects_start_after_end() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Interval(5, 3));
    }

    @Test
    public void no_overlap_if_adjacent() {
        var i1 = new Interval(3, 5);
        var i2 = new Interval(5, 7);
        assertThat(i1.overlapsWith(i2)).isFalse();
        assertThat(i2.overlapsWith(i1)).isFalse(); // symmetry
    }

    @Test
    public void no_overlap() {
        var i1 = new Interval(1, 2);
        var i2 = new Interval(3, 4);
        assertThat(i1.overlapsWith(i2)).isFalse();
        assertThat(i2.overlapsWith(i1)).isFalse(); // symmetry
    }

    @Test
    public void overlap_completely() {
        var i1 = new Interval(1, 10);
        var i2 = new Interval(3, 4);
        assertThat(i1.overlapsWith(i2)).isTrue();
        assertThat(i2.overlapsWith(i1)).isTrue(); // symmetry
    }

    @Test
    public void overlap_partially() {
        var i1 = new Interval(1, 5);
        var i2 = new Interval(3, 10);
        assertThat(i1.overlapsWith(i2)).isTrue();
        assertThat(i2.overlapsWith(i1)).isTrue(); // symmetry
    }

    @Test
    public void overlap_same() {
        var i1 = new Interval(1, 5);
        var i2 = new Interval(1, 5);
        assertThat(i1.overlapsWith(i2)).isTrue();
        assertThat(i2.overlapsWith(i1)).isTrue(); // symmetry
    }
}
