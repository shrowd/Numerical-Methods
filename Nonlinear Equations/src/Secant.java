import java.lang.Math;

public class Secant {
    public static double fun(double x) {
        return 6 * Math.pow(x, 2) + 13 * x - 5;
    }

    public static double d2(double x) {
        return 12;
    }

    public static void compute(double a, double b, double e) {
        if (fun(a) * fun(b) >= 0) {
            return;
        }

        int i = 0;
        double result = 0;
        double x0;
        double temp;

        if (Math.signum(fun(a)) == Math.signum(d2(a))) {
            x0 = b;
            temp = a;
        } else {
            x0 = a;
            temp = b;
        }

        while (Math.abs(fun(x0)) >= e) {
            if (temp == a) {
                result = x0 - (fun(x0) / (fun(x0) - fun(a))) * (x0 - a);
            } else result = x0 - (fun(x0) / (fun(b) - fun(x0))) * (b - x0);
            x0 = result;
            i++;
        }

        System.out.println("Metoda siecznych:");
        System.out.println("x = " + result + " Liczba iteracji: " + i);
        System.out.println("F(x) = " + fun(result));
    }

    public static void main(String[] args) {
        compute(0.1, 4, 0.01);
    }
}
