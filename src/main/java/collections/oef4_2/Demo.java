package collections.oef4_2;

import java.util.HashSet;
import java.util.Set;

public class Demo {

    static class MyObject {
        public int hashCode = 1;

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

    public static void main(String[] args) {
        Set<MyObject> set = new HashSet<>();

        MyObject obj = new MyObject();
        set.add(obj);
        System.out.println("Before changing hashcode");
        System.out.println("Contains: " + set.contains(obj));

        obj.hashCode++;

        System.out.println("After changing hashcode");
        System.out.println("Contains: " + set.contains(obj));
    }
}
