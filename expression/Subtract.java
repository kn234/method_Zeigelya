package expression;

public class Subtract extends Operation {
    public Subtract(Expressions a, Expressions b) {
        super(a, b, "-");
    }

    @Override
    float calc(float a, float b) {
        return a - b;
    }

    @Override
    public String toMiniString() {
        if (b.getClass() == Subtract.class || b.getClass() == Add.class) {
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
