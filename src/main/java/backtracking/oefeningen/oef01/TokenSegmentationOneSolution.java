package backtracking.oefeningen.oef01;

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
        if (strToTokenize.isEmpty()) return tokensSoFar;
        if (tokens.isEmpty()) return null;

        for (var token : tokens) {
            if (strToTokenize.startsWith(token)) {
                String rest = strToTokenize.substring(token.length());
                tokensSoFar.add(token);
                // tokens.remove(token)
                var newTokens = new ArrayList<>(tokens);
                newTokens.remove(token);
                var solution = findSegmentation_worker(newTokens, rest, tokensSoFar);
                if (solution != null) {
                    return solution;
                } else {
                    // no solution
                    // tokens.add(token)
                    tokensSoFar.removeLast();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        var tokens = new ArrayList<>(List.of("s", "an", "ca", "cat", "dog", "and", "sand", "dogs"));
        var str = "catsanddogs";
        var solution = findSegmentation(tokens, str);
        System.out.println(solution);
    }
}