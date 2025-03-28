package generics.oef02;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class RepositoryTests {
    record Person(String name) {}

    @Test
    public void add_appears_in_find_all() {
        var repo = new Repository<String, Person>();
        var john = new Person("John");
        repo.add("123", john);
        assertThat(repo.findAll()).contains(john);
    }

    @Test
    public void find_by_id_returns_correct_object() {
        var repo = new Repository<String, Person>();
        var john = new Person("John");
        var mary = new Person("Mary");
        repo.add("123", john);
        repo.add("456", mary);
        assertThat(repo.findById("123")).isEqualTo(john);
    }

    @Test
    public void find_by_id_not_found() {
        var repo = new Repository<String, Person>();
        var john = new Person("John");
        var mary = new Person("Mary");
        repo.add("123", john);
        repo.add("456", mary);
        assertThatThrownBy(() -> repo.findById("999")).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void update_updates() {
        var repo = new Repository<String, Person>();
        var john = new Person("John");
        var mary = new Person("Mary");
        repo.add("123", john);
        repo.update("123", mary);
        assertThat(repo.findById("123")).isEqualTo(mary);
    }

    @Test
    public void remove_removes() {
        var repo = new Repository<String, Person>();
        var john = new Person("John");
        var mary = new Person("Mary");
        repo.add("123", john);
        repo.add("456", mary);
        repo.remove("123");
        assertThat(repo.findAll()).doesNotContain(john);
    }
}
