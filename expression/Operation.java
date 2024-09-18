package expression;


public abstract class Operation implements Expressions {
    Expressions a, b;
    String action;


    public Operation(Expressions a, Expressions b, String ac) {
        this.a = a;
        this.b = b;
        this.action = ac;
    }

    public Operation() {

    }


    public float evaluate(float x) {
        float evalA = this.a.evaluate(x);
        float evalB = this.b.evaluate(x);

        return calc(evalA, evalB);
    }

    abstract float calc(float a, float b);

    public float evaluate(float x, float y, float z) {
        float evalA = this.a.evaluate(x, y, z);
        float evalB = this.b.evaluate(x, y, z);

        return calc(evalA, evalB);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + " " + action + " " + b.toString() + ")";
    }

    @Override
    public int hashCode() {
        return action.hashCode() + a.hashCode() * 13 + b.hashCode() * 111;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                if (action.equals(((Operation) obj).action)) {
                    return this.a.equals(((Operation) obj).a) && this.b.equals(((Operation) obj).b);
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public String inBracket(Expressions a) {
        return "(" + a.toMiniString() + ")";
    }

}
