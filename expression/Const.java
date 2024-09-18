package expression;

public class Const implements Expressions {
    float value;

    public Const(float value) {
        this.value = value;
    }

    @Override
    public float evaluate(float x) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString((int) value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                return this.value == ((Const) obj).value;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }

    @Override
    public float evaluate(float x, float y, float z) {
        return value;
    }
}
