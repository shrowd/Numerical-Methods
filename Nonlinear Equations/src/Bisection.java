import java.lang.Math;

public class Bisection {
    public static double fun(double x) {
        return 6 * Math.pow(x, 2) + 13 * x - 5;
    }

    public static void compute(double a, double b, double e) {
        if (fun(a) * fun(b) >= 0) {
            return;
        }

        int i = 0;
        double result = (a + b) / 2;

        while (Math.abs(fun(result)) >= e) {
            result = (a + b) / 2;
            if (fun(result) == 0.0) break;
            else if (fun(result) * fun(a) < 0) b = result;
            else a = result;
            i++;
        }

        System.out.println("Metoda bisekcji:");
        System.out.println("x = " + result + " Liczba iteracji: " + i);
        System.out.println("F(x) = " + fun(result));
    }

    public static void main(String[] args) {
        compute(0.1, 4, 0.01);
    }
}
