import java.lang.Math;

public class Simpson {

    public static double fun(double x) {
        return (0.3 * x + 0.2) / (1.4 - Math.cos(Math.pow(x, 2) + 0.3));
    }

    public static double integrate(double a, double b, int n) {

        double h = (b - a) / n;

        double result = fun(a) + fun(b);

        for (int i = 1; i < n; i++) {

            double x = a + i * h;

            if (i % 2 == 0) {
                result += 2 * fun(x);

            } else {
                result += 4 * fun(x);
            }
        }

        return result * h / 3;
    }

    public static void main(String[] args) {
        System.out.println("Metoda Simpsona: " + integrate(1.2, 2.2, 10));
    }
}
