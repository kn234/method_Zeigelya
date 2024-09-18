package expression;

public class Variable implements Expressions {
    String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    @Override
    public float evaluate(float x) {
        return x;
    }

    @Override
    public float evaluate(float x, float y, float z) {
        if (varName.equals("x")) {
            return x;
        } else if (varName.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public String toString() {
        return varName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                return this.varName.equals(((Variable) obj).varName);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return varName.hashCode();
    }


}
