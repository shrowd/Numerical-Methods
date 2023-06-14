import java.lang.Math;

public class Newton {
    public static double fun(double x) {
        return 6 * Math.pow(x, 2) + 13 * x - 5;
    }

    public static double d1(double x) {
        return 12 * x + 13;
    }

    public static double d2(double x) {
        return 12;
    }

    public static void compute(double a, double b, double e) {
        if (fun(a) * fun(b) >= 0) {
            return;
        }
        if (d1(a) * d1(b) >= 0 && d2(a) * d2(b) >= 0) System.out.println("Warunki zbieżności spełnione");
        else System.out.println("Warunki zbieżności niespełnione");

        double x0;
        int i = 0;
        double result = 0;

        if (Math.signum(fun(a)) == Math.signum(d2(a))) {
            x0 = a;
        } else x0 = b;

        while (Math.abs(fun(x0)) >= e) {
            result = x0 - (fun(x0) / d1(x0));
            x0 = result;
            i++;
        }

        System.out.println("Metoda stycznych:");
        System.out.println("x = " + result + " Liczba iteracji: " + i);
        System.out.println("F(x) = " + fun(result));
    }

    public static void main(String[] args) {
        compute(0.1, 4, 0.01);
    }
}
