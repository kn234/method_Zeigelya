package expression;

public class Negate extends Operation {
    public Negate(Expressions a) {
        super(a, new Const(0), "-");
    }

    @Override
    float calc(float a, float b) {
        return -a;
    }

    @Override
    public float evaluate(float x, float y, float z) {
        float evalA = this.a.evaluate(x, y, z);
        return calc(evalA, 0);
    }

    @Override
    public String toString() {
        return this.action + this.a;
    }
    @Override
    public String toMiniString() {
        return this.action + this.a;
    }
}
