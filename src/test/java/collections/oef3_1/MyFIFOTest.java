package collections.oef3_1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MyFIFOTest {

    @Test
    public void empty_fifo_size_0() {
        MyFIFO<String> fifo = new MyFIFO<>(5);
        assertThat(fifo.size()).isEqualTo(0);
    }

    @Test
    public void fifo_one_element() {
        MyFIFO<String> fifo = new MyFIFO<>(5);
        fifo.add("first");
        assertThat(fifo.size()).isEqualTo(1);
        assertThat(fifo.poll()).isEqualTo("first");
    }

    @Test
    public void fifo_maximum_capacity() {
        MyFIFO<String> fifo = new MyFIFO<>(5);
        for (var e : List.of("first", "second", "third", "fourth", "fifth")) {
            assertThat(fifo.add(e)).isTrue();
        }
        assertThat(fifo.size()).isEqualTo(5);

        assertThat(fifo.add("sixth")).isFalse();
        assertThat(fifo.size()).isEqualTo(5);
    }

    @Test
    public void fifo_poll_correct_order() {
        MyFIFO<String> fifo = new MyFIFO<>(5);
        for (var e : List.of("first", "second", "third", "fourth", "fifth")) {
            assertThat(fifo.add(e)).isTrue();
        }
        assertThat(fifo.poll()).isEqualTo("first");
        assertThat(fifo.poll()).isEqualTo("second");
        assertThat(fifo.poll()).isEqualTo("third");
        assertThat(fifo.size()).isEqualTo(2);
        assertThat(fifo.poll()).isEqualTo("fourth");
        assertThat(fifo.poll()).isEqualTo("fifth");
        assertThat(fifo.size()).isEqualTo(0);
    }
}
