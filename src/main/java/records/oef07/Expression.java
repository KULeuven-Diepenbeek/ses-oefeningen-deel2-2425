package records.oef07;


public sealed interface Expression {
    int priority();

    boolean contains(Variable var);

    record Literal(double value) implements Expression {
        @Override
        public int priority() {
            return 100;
        }

        @Override
        public boolean contains(Variable var) {
            return false;
        }

        @Override
        public String toString() {
            return ExpressionUtils.prettyPrint(this);
        }
    }

    record Variable(String name) implements Expression{
        @Override
        public int priority() {
            return 100;
        }

        @Override
        public boolean contains(Variable var) {
            return this.equals(var);
        }

        @Override
        public String toString() {
            return ExpressionUtils.prettyPrint(this);
        }
    }

    record Sum(Expression left, Expression right) implements Expression {
        @Override
        public int priority() {
            return 10;
        }

        @Override
        public boolean contains(Variable var) {
            return left.contains(var) || right.contains(var);
        }

        @Override
        public String toString() {
            return ExpressionUtils.prettyPrint(this);
        }
    }

    record Product(Expression left, Expression right) implements Expression {
        @Override
        public int priority() {
            return 20;
        }

        @Override
        public boolean contains(Variable var) {
            return left.contains(var) || right.contains(var);
        }

        @Override
        public String toString() {
            return ExpressionUtils.prettyPrint(this);
        }
    }

    record Power(Expression base, Literal power) implements Expression {
        @Override
        public int priority() {
            return 30;
        }

        @Override
        public boolean contains(Variable var) {
            return base.contains(var);
        }

        @Override
        public String toString() {
            return ExpressionUtils.prettyPrint(this);
        }
    }
}
