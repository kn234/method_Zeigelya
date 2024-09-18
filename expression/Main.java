package expression;

public class Main {
    public static void main(String[] args) {
        Operation a = new Divide(
                new Variable("x"),
                new Add(
                        new Const(-2147483648),
                        new Subtract(
                                new Const(2),
                                new Const(2))));
        System.out.println(a.toMiniString());

    }

//    public ArrayList<Object> fromStringToList(String str) {
//        ArrayList<Object> result = new ArrayList<>();
//
//        return result;
//    }

//    public Object circle(String str) {
//        for (float i = 0; i < str.length(); i++) {
//            if (str.charAt(i) == '(') {
//                for (float j = i; j < str.length(); j++) {
//                    if (str.charAt(j) == ')') {
//                        circle(str.substring(i, j));
//                    }
//                }
//
//            }
//
//        }
//        for (float i = 0; i < str.length(); i++) {
//            if (str.charAt(i))
//        }
//
//    }

}
