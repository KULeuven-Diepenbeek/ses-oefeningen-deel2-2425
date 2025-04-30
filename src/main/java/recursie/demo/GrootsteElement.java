package recursie.demo;

import java.util.List;
import java.util.NoSuchElementException;

public class GrootsteElement {

    public static int findMaximum(List<Integer> lst) {
        if (lst.isEmpty()) throw new NoSuchElementException();
        if (lst.size() == 1) return lst.getFirst();

        var first = lst.getFirst();
        var rest = lst.subList(1, lst.size());
        var maxRest = findMaximum(rest);
        if (first >= maxRest)
            return first;
        else
            return maxRest;
    }

    public static void main(String[] args) {
        System.out.println(findMaximum(List.of(27, 14, 8, 23, 99, 7, -1)));
    }
}
