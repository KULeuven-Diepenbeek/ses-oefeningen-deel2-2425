package backtracking.demo;

import java.util.ArrayList;
import java.util.List;

public class TokenSegmentationOneSolution {

    public static List<String> findSegmentation(List<String> tokens, String str) {
        return findSegmentation_worker(tokens, str, new ArrayList<>());
    }

    private static List<String> findSegmentation_worker(
            List<String> tokens,
            String strToTokenize,
            List<String> tokensSoFar
    ) {
        System.out.println("'" + strToTokenize + "' " + tokensSoFar);
       if (strToTokenize.isEmpty()) return tokensSoFar;

       for (var token : tokens) {
           if (strToTokenize.startsWith(token)) {
               String rest = strToTokenize.substring(token.length());
               tokensSoFar.add(token);
               var solution = findSegmentation_worker(tokens, rest, tokensSoFar);
               if (solution != null) {
                   return solution;
               } else {
                   // no solution
                   tokensSoFar.removeLast();
               }
           }
       }
       return null;
    }

    public static void main(String[] args) {
        var tokens = List.of("s", "an", "ca", "cat", "dog", "and", "sand", "dogs");
        var str = "catsanddogs";
        var solution = findSegmentation(tokens, str);
        System.out.println(solution);
    }
}