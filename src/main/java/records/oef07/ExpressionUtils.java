package records.oef07;

import records.oef07.Expression.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ExpressionUtils {

    /**
     * Voeg haakjes toe als currentPriority lager is dan outerPriority.
     */
    private static String withParens(String s, int currentPriority, int outerPriority) {
        if (currentPriority < outerPriority) return "(" + s + ")";
        return s;
    }

    public static String prettyPrint(Expression expr) {
        return switch(expr) {
            case Literal(double value) -> "" + value;
            case Variable(String name) -> name;
            case Power(Expression base, Literal exponent) ->
                    withParens(prettyPrint(base), base.priority(), expr.priority()) + "^"+ prettyPrint(exponent);
            case Product(var left, var right) ->
                    withParens(prettyPrint(left), left.priority(), expr.priority()) + " * " + withParens(prettyPrint(right), right.priority(), expr.priority());
            case Sum(var left, var right) ->
                    withParens(prettyPrint(left), left.priority(), expr.priority()) + " + " + withParens(prettyPrint(right), right.priority(), expr.priority());
        };
    }

    public static Expression simplify(Expression expr) {
        return switch(expr) {
            case Literal literal -> literal;
            case Variable variable -> variable;

            /* Vereenvoudigen van macht */
            case Power(Literal(var base), Literal(var exp)) -> new Literal(Math.pow(base, exp));
            case Power(Expression base, Literal(var exp)) when exp == 0 -> new Literal(1.0);
            case Power(Expression base, Literal(var exp)) when exp == 1 -> simplify(base);
            case Power(Expression base, Literal exp) -> new Power(simplify(base), exp);

            /* Vereenvoudigen van product */
            // 3 * 2 => 6 */
            case Product(Literal(var v1), Literal(var v2)) -> new Literal(v1 * v2);
            // 3 * (2 * ...) => 6 * ...
            case Product(Literal(var v1), Product(Literal(var v2), var rest)) -> simplify(new Product(new Literal(v1 * v2), rest));
            // 0 * ... => 0
            case Product(Literal(var v), Expression right) when v == 0 -> new Literal(0);
            // 1 * ... => ...
            case Product(Literal(var v), Expression right) when v == 1 -> simplify(right);
            // ... * 0 => 0
            case Product(Expression left, Literal(var v)) when v == 0 -> new Literal(0);
            // ... * 1 => ...
            case Product(Expression left, Literal(var v)) when v == 1 -> simplify(left);
            // ... * 7 => 7 * ...  (constanten naar links brengen)
            case Product(Expression left, Literal right) -> simplify(new Product(right, simplify(left)));
            // ... * ... => beide factoren afzonderlijk vereenvoudigen
            case Product(Expression left, Expression right) -> new Product(simplify(left), simplify(right));

            /* Vereenvoudigen van som */
            // 3 + 2 => 5
            case Sum(Literal(var v1), Literal(var v2)) -> new Literal(v1 + v2);
            // 3 + (2 + ...) => 5 + ...
            case Sum(Literal(var v1), Sum(Literal(var v2), var rest)) -> simplify(new Sum(new Literal(v1 + v2), rest));
            // 0 + ... => ...
            case Sum(Literal(var v), Expression right) when v == 0 -> simplify(right);
            // ... + 0 => ...
            case Sum(Expression left, Literal(var v)) when v == 0 -> simplify(left);
            // ... + 3 => 3 + ...  (constante termen naar links brengen)
            case Sum(Expression left, Literal right) -> simplify(new Sum(right, simplify(left)));
            // ... + ... => beide termen afzonderlijk vereenvoudigen
            case Sum(Expression left, Expression right) -> new Sum(simplify(left), simplify(right));
        };
    }

    /** Toekenning van een waarde aan een variabele */
    public record Assignment(String varName, double value) {}

    /** Hulpfunctie om een waarde op te zoeken in een lijst van assignments */
    private static double findValue(ArrayList<Assignment> assignments, String varName) {
        for (var assignment : assignments) {
            if (assignment.varName().equals(varName)) return assignment.value;
        }
        throw new NoSuchElementException("No such variable: " + varName);
    }

    public static double evaluate(Expression expr, ArrayList<Assignment> assignments) {
        return switch(expr) {
            case Literal(var value) -> value;
            case Variable(var name) -> findValue(assignments, name);
            case Sum(var left, var right) -> evaluate(left, assignments) + evaluate(right, assignments);
            case Product(var left, var right) -> evaluate(left, assignments) * evaluate(right, assignments);
            case Power(var base, var exp) -> Math.pow(evaluate(base, assignments), evaluate(exp, assignments));
        };
    }

    public static Expression differentiate(Expression expr, Variable var) {
        // Optimalisatie: als expressie niet afhangt van variabele, is het resultaat sowieso 0
        if (!expr.contains(var)) return new Literal(0);

        return simplify(switch(expr) {
            case Literal literal -> new Literal(0);
            case Power(Expression base, Literal(var exp)) when base.contains(var) ->
                    new Product(new Literal(exp), new Power(base, new Literal(exp - 1)));
            case Power(Expression base, Literal(var exp)) -> new Literal(0);
            case Product(Expression left, Expression right) ->
                    new Sum(new Product(differentiate(left, var), right),
                            new Product(left, differentiate(right, var)));
            case Sum(Expression left, Expression right) ->
                    new Sum(differentiate(left, var), differentiate(right, var));
            case Variable variable when variable.equals(var) -> new Literal(1);
            case Variable variable -> new Literal(0);
        });
    }
}
