import java.lang.Math;

public class Trapezoid {
    public static double fun(double x) {
        return (0.3 * x + 0.2) / (1.4 - Math.cos(Math.pow(x, 2) + 0.3));
    }

    public static double integrate(double a, double b, int n) {
        double result = 0;
        double h = (b - a) / n;

        for (int i = 0; i < n; i++) {
            double fa = a + h * i;
            double fb = a + h * (i + 1);

            result += (fun(fa) + fun(fb)) / 2 * h;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Metoda Trapezów: " + integrate(1.2, 2.2, 10));
    }
}
