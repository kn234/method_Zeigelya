package expression;

import expression.common.Op;

public class Divide extends Operation {


    public Divide(Expressions a, Expressions b) {
        super(a, b, "/");
    }


    @Override
    float calc(float a, float b) {
        return a / b;
    }

    @Override
    public String toMiniString() {
        if (a.getClass() == Add.class && b.getClass() == Add.class) {
            return inBracket(a) + " " + action + " " + inBracket(b);
        } else if (a.getClass() == Subtract.class && b.getClass() == Subtract.class) {
            return inBracket(a) + " " + action + " " + inBracket(b);
        } else if ((a.getClass() == Add.class && b.getClass() == Subtract.class)
                || (a.getClass() == Subtract.class && b.getClass() == Add.class)) {
            return inBracket(a) + " " + action + " " + inBracket(b);
        } else if (a.getClass() == Subtract.class || a.getClass() == Add.class) {
            return inBracket(a) + " " + action + " " + b.toMiniString();
        } else if (b.getClass() == Subtract.class || b.getClass() == Add.class || b.getClass() == Multiply.class || b.getClass() == Divide.class) {
            return a.toMiniString() + " " + action + " " + inBracket(b);
        } else if (a.getClass() == Negate.class && b.getClass() == Negate.class) {
            return inBracket(a) + " " + action + " " + inBracket(b);
        } else if (a.getClass() == Negate.class) {
            return inBracket(a) + " " + action + " " + b.toMiniString();
        } else if (b.getClass() == Negate.class) {
            return a.toMiniString() + " " + action + " " + inBracket(b);
        } else {
            return a.toMiniString() + " " + action + " " + b.toMiniString();
        }
    }
}
