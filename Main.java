import method_Zeigelya.Slau;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Slau eqa = new Slau("5*x - y + 3*z - 5", "x - 4*y + 2*z - 20", "2*x - y + 5*z - 10");
        System.out.println(eqa.toString());
        System.out.println(eqa.solutionSlau(5));


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