package collections.oef5_3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class MultiMapTest {

    @Test
    public void empty_multimap() {
        var mm = new MultiMap<Integer, String>();
        assertThat(mm.size()).isEqualTo(0);
    }

    @Test
    public void add_one_element() {
        var mm = new MultiMap<Integer, String>();
        mm.put(1, "first");
        assertThat(mm.size()).isEqualTo(1);
        assertThat(mm.get(1)).containsExactly("first");
    }

    @Test
    public void add_multiple_with_same_key() {
        var mm = new MultiMap<Integer, String>();
        mm.put(1, "first");
        mm.put(1, "second");
        mm.put(1, "third");
        mm.put(2, "fourth");
        mm.put(2, "fifth");
        assertThat(mm.size()).isEqualTo(2);
        assertThat(mm.get(1)).containsExactlyInAnyOrder("first", "second", "third");
        assertThat(mm.get(2)).containsExactlyInAnyOrder("fourth", "fifth");
    }

    @Test
    public void remove_one_value() {
        var mm = new MultiMap<Integer, String>();
        mm.put(1, "first");
        mm.put(1, "second");
        mm.put(1, "third");
        assertThat(mm.size()).isEqualTo(1);
        mm.remove(1, "second");
        assertThat(mm.size()).isEqualTo(1);
        assertThat(mm.get(1)).containsExactlyInAnyOrder("first", "third");
    }
}
