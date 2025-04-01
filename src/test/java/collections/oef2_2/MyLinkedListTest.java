package collections.oef2_2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    public void add_first_element() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("element0");
        assertThat(lst.size()).isEqualTo(1);
        assertThat(lst.get(0)).isEqualTo("element0");
    }

    @Test
    public void add_more_elements() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("element0");
        lst.add("element1");
        assertThat(lst.size()).isEqualTo(2);
        assertThat(lst.get(0)).isEqualTo("element0");
        assertThat(lst.get(1)).isEqualTo("element1");

        lst.add("element2");
        assertThat(lst.size()).isEqualTo(3);
        assertThat(lst.get(0)).isEqualTo("element0");
        assertThat(lst.get(1)).isEqualTo("element1");
        assertThat(lst.get(2)).isEqualTo("element2");
    }

    @Test
    public void remove_elements() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("element0");
        lst.add("element1");
        lst.add("element2");

        lst.remove(1);
        assertThat(lst.size()).isEqualTo(2);
        assertThat(lst.get(0)).isEqualTo("element0");
        assertThat(lst.get(1)).isEqualTo("element2");

        lst.remove(1);
        assertThat(lst.size()).isEqualTo(1);
        assertThat(lst.get(0)).isEqualTo("element0");
    }

    @Test
    public void get_from_empty() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        assertThatIndexOutOfBoundsException().isThrownBy(() -> lst.get(0));
    }

    @Test
    public void remove_from_empty() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        assertThatIndexOutOfBoundsException().isThrownBy(() -> lst.remove(0));
    }

    @Test
    public void get_from_single() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("element0");
        assertThat(lst.get(0)).isEqualTo("element0");
    }

    @Test
    public void remove_from_single() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        lst.add("element0");
        lst.remove(0);
        assertThat(lst.size()).isEqualTo(0);
    }
}
