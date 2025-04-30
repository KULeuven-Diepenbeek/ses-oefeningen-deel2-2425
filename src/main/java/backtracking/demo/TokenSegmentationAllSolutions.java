package backtracking.demo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TokenSegmentationAllSolutions {

    public static List<List<String>> findAllSegmentations(List<String> tokens, String str) {
        return findSegmentation_worker(tokens, str,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    private static List<List<String>> findSegmentation_worker(
            List<String> tokens,
            String strToTokenize,
            List<String> tokensSoFar,
            List<List<String>> solutionsSoFar
    ) {
       if (strToTokenize.isEmpty()) {
           solutionsSoFar.add(List.copyOf(tokensSoFar));
           return solutionsSoFar;
       }

       for (var token : tokens) {
           if (strToTokenize.startsWith(token)) {
               String rest = strToTokenize.substring(token.length());
               tokensSoFar.add(token);
               findSegmentation_worker(tokens, rest,
                       tokensSoFar, solutionsSoFar);
               tokensSoFar.removeLast();
           }
       }
       return solutionsSoFar;
    }

    public static void main(String[] args) {
        var tokens = List.of("s", "an", "ca", "cat", "dog", "and", "sand", "dogs");
        var str = "catsanddogs";
        var solutions = findAllSegmentations(tokens, str);
        System.out.println(solutions);
    }
}