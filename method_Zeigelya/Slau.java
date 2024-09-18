package method_Zeigelya;

import expression.*;

import expression.parser.Lexem;

import static expression.parser.Lexem.expr;
import static expression.parser.Lexem.parseLexem;

public class Slau {
    TripleExpression equation1;
    TripleExpression equation2;
    TripleExpression equation3;


    public String toString() {
        StringBuilder result = new StringBuilder(equation1.toMiniString());
        result.append("\n").append(equation2.toMiniString()).append("\n").append(equation3.toMiniString());
        return result.toString();
    }


    public String solutionSlau(int iteration) {
        StringBuilder result = new StringBuilder();
        result.append("i").append("\t").append("x").append("\t").append("y").append("\t").append("z").append("\t").append("Delta x").append("\t").append("Delta y").append("\t").append("Delta z").append("\n");

        float x, y, z, xPrev, yPrev, zPrev, xDelta, yDelta, zDelta;
        x = (-equation1.evaluate(0, 0, 0)) / (equation1.evaluate(1, 0, 0) - equation1.evaluate(0, 0, 0));

        xPrev = x;

        y = (-equation2.evaluate(0, 0, 0)) / (equation2.evaluate(0, 1, 0) - equation2.evaluate(0, 0, 0));

        yPrev = y;

        z = (-equation3.evaluate(0, 0, 0)) / (equation3.evaluate(0, 0, 1) - equation3.evaluate(0, 0, 0));

        zPrev = z;

        int i = 0;
        result.append(i).append("\t").append(x).append("\t").append(y).append("\t").append(z).append("\t").append("-").append("\t").append("-").append("\t").append("-").append("\n");
        while (i < iteration) {
            x = (-equation1.evaluate(0, 0, 0) - (equation1.evaluate(0, 1, 0) - equation1.evaluate(0, 0, 0)) * y - (equation1.evaluate(0, 0, 1) - equation1.evaluate(0, 0, 0)) * z) /
                    (equation1.evaluate(1, 0, 0) - equation1.evaluate(0, 0, 0));
            y = (-equation2.evaluate(0, 0, 0) - (equation2.evaluate(1, 0, 0) - equation2.evaluate(0, 0, 0)) * x - (equation2.evaluate(0, 0, 1) - equation2.evaluate(0, 0, 0)) * z) /
                    (equation2.evaluate(0, 1, 0) - equation2.evaluate(0, 0, 0));
            z = (-equation3.evaluate(0, 0, 0) - (equation3.evaluate(1, 0, 0) - equation3.evaluate(0, 0, 0)) * x - (equation3.evaluate(0, 1, 0) - equation3.evaluate(0, 0, 0)) * y) /
                    (equation3.evaluate(0, 0, 1) - equation3.evaluate(0, 0, 0));

            xDelta = Math.abs(x-xPrev) / Math.abs(x);
            xPrev = x;
            yDelta = Math.abs(y-yPrev) / Math.abs(y);
            yPrev = y;
            zDelta = Math.abs(z-zPrev) / Math.abs(z);
            zPrev = z;

            i++;
            result.append(i).append("\t").append(x).append("\t").append(y).append("\t").append(z).append("\t").append(xDelta).append("\t").append(yDelta).append("\t").append(zDelta).append("\n");
        }

        return result.toString();
    }

    public Slau(String new_eq1, String new_eq2, String new_eq3) {
        equation1 = expr(new Lexem.LexemBuffer(parseLexem(new_eq1)));
        equation2 = expr(new Lexem.LexemBuffer(parseLexem(new_eq2)));
        equation3 = expr(new Lexem.LexemBuffer(parseLexem(new_eq3)));
    }
}
//Дана СИСТЕМА УРАВНЕНИЙ
//     5x1 - x2 + 3x3 = 5                             Погрешность: Deltai(k) = abs(xi(k) - xi(k - 1)) / abs(xi(k))
//     x1 - 4x2 + 2x3 = 20
//     2x1 - x2 + 5x3 = 10   Для получения ответа должно соблюдаться условие:             Deltai < 0,01
//Нулевое приближение
//     x1(0) = b1 / a11 = 5 / 5 = 1
//     x2(0) = b2 / a22 = 20 / (-4) = -5
//     x3(0) = b3 / a33 = 10 / 5 = 2
//Первое приближение
//     x1(1) = (5 + x2(0) - 3x3(0)) / 5 = (5 - 5 - 6) / 5 = -1,2
//     x2(1) = (20 - x1(1) - 2x3(0)) / (-4) = (20 + 1,2 - 4) / (-4) = -4,3
//     x3(1) = (10 - 2x1(1) - x2(1)) / 5 = (10 - 2,4 + 4,3) / 5 = 1,62
//
//
//
//