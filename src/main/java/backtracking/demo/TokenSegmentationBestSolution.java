package backtracking.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TokenSegmentationBestSolution {

    public static List<String> findShortestSegmentation(List<String> tokens, String str) {
        return findSegmentation_worker(tokens, str,
                new ArrayList<>(),
                null
        );
    }

    private static List<String> findSegmentation_worker(
            List<String> tokens,
            String strToTokenize,
            List<String> tokensSoFar,
            List<String> bestSoFar
    ) {
       if (strToTokenize.isEmpty()) {
           if (bestSoFar == null) {
               return tokensSoFar;
           } else if (tokensSoFar.size() < bestSoFar.size()) {
               return tokensSoFar;
           } else {
               return bestSoFar;
           }
       }
       if (bestSoFar != null && bestSoFar.size() <= tokensSoFar.size()) {
           return bestSoFar;
       }
       for (var token : tokens) {
           if (strToTokenize.startsWith(token)) {
               String rest = strToTokenize.substring(token.length());
               var newTokensSoFar = new ArrayList<>(tokensSoFar);
               newTokensSoFar.add(token);
               bestSoFar = findSegmentation_worker(tokens, rest,
                       newTokensSoFar, bestSoFar);
           }
       }
       return bestSoFar;
    }

    public static void main(String[] args) {
        var tokens = List.of("s", "an", "ca", "cat", "dog", "and", "sand", "dogs");
        var str = "catsanddogs";
        var solution = findShortestSegmentation(tokens, str);
        System.out.println(solution);
    }
}