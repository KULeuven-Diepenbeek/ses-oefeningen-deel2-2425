package records.oef07;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import records.oef07.Expression.*;

import java.util.ArrayList;

import static records.oef07.ExpressionUtils.*;

public class ExpressionTests {

    @Test
    public void testLiteral() {
        var lit = new Literal(3.0);
        assertThat(lit.value()).isEqualTo(3);
    }

    @Test
    public void testVariable() {
        var var = new Variable("x");
        assertThat(var.name()).isEqualTo("x");
    }

    @Test
    public void pretty_print() {
        var poly = new Sum(
                new Product(
                        new Literal(3),
                        new Power(
                                new Variable("x"),
                                new Literal(2))),
                new Literal(5));
        assertThat(prettyPrint(poly)).isEqualTo("3.0 * x^2.0 + 5.0");
    }

    @Test
    public void pretty_print_order() {
        var poly = new Product(
                new Sum(new Variable("x"),
                        new Literal(1.0)),
                new Sum(new Variable("y"), new Literal(2.0)));
        assertThat(prettyPrint(poly)).isEqualTo("(x + 1.0) * (y + 2.0)");
    }

    @Test
    public void simplify_times_one_right() {
        var expr = new Product(new Variable("x"), new Literal(1.0));
        assertThat(simplify(expr)).isEqualTo(new Variable("x"));
    }

    @Test
    public void simplify_times_one_left() {
        var expr = new Product(new Literal(1.0), new Variable("x"));
        assertThat(simplify(expr)).isEqualTo(new Variable("x"));
    }

    @Test
    public void simplify_times_one_right_multiple() {
        var expr = new Product(new Product(new Variable("x"), new Literal(1.0)), new Literal(1.0));
        assertThat(simplify(expr)).isEqualTo(new Variable("x"));
    }

    @Test
    public void simplify_times_zero_right() {
        var expr = new Product(new Variable("x"), new Literal(0.0));
        assertThat(simplify(expr)).isEqualTo(new Literal(0));
    }

    @Test
    public void simplify_times_zero_left() {
        var expr = new Product(new Literal(0.0), new Variable("x"));
        assertThat(simplify(expr)).isEqualTo(new Literal(0));
    }

    @Test
    public void simplify_plus_zero_right() {
        var expr = new Sum(new Variable("x"), new Literal(0.0));
        assertThat(simplify(expr)).isEqualTo(new Variable("x"));
    }

    @Test
    public void simplify_plus_zero_left() {
        var expr = new Sum(new Literal(0.0), new Variable("x"));
        assertThat(simplify(expr)).isEqualTo(new Variable("x"));
    }

    @Test
    public void simplify_plus_literals() {
        var expr = new Sum(new Literal(2.0), new Literal(5.0));
        assertThat(simplify(expr)).isEqualTo(new Literal(7.0));
    }

    @Test
    public void simplify_times_literals() {
        var expr = new Product(new Literal(2.0), new Literal(5.0));
        assertThat(simplify(expr)).isEqualTo(new Literal(10.0));
    }

    @Test
    public void evaluate() {
        var poly = new Sum(
                new Product(
                        new Literal(3),
                        new Power(
                                new Variable("x"),
                                new Literal(2))),
                new Literal(5));
        var assignment = new ArrayList<Assignment>();
        assignment.add(new ExpressionUtils.Assignment("x", 7.0));
        assertThat(ExpressionUtils.evaluate(poly, assignment)).isEqualTo(152);
    }

    @Test
    public void diff() {
        var poly = new Sum(
                new Product(
                        new Literal(3),
                        new Power(
                                new Variable("x"),
                                new Literal(2))),
                new Product(
                        new Literal(5),
                        new Variable("x")));
        assertThat(ExpressionUtils.differentiate(poly, new Variable("x"))).isEqualTo(
                new Sum(
                        new Product(
                                new Literal(6.0),
                                new Variable("x")),
                        new Literal(5.0)));
    }


}
