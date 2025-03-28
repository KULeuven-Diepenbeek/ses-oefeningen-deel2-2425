package generics.oef02;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Repository<I, T> {

    public ArrayList<T> findAll() {
        ArrayList<T> result = new ArrayList<>();
        for (var entry : objects) {
            result.add(entry.obj());
        }
        return result;
    }

    public T findById(I id) {
        for (var entry : objects) {
            if (entry.id().equals(id)) {
                return entry.obj();
            }
        }
        throw new NoSuchElementException();
    }

    public void update(I id, T newObj) {
        for (int i = 0; i < objects.size(); i++) {
            var entry = objects.get(i);
            if (entry.id().equals(id)) {
                objects.set(i, new Entry<>(id, newObj));
            }
        }
    }

    public void remove(I id) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            var entry = objects.get(i);
            if (entry.id().equals(id)) {
                objects.remove(i);
            }
        }
    }

    private record Entry<I, T>(I id, T obj) {}

    private ArrayList<Entry<I, T>> objects = new ArrayList<>();

    public void add(I id, T obj) {
        this.objects.add(new Entry<>(id, obj));
    }
}
