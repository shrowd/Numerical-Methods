public class PolynomialApproximation {

    public static double fun(double x) {
        return Math.sqrt(8 * Math.pow(x, 2) + 3);
    }

    public static double legendre(int n, double x) {
        if (n == 0) {
            return 1.0;
        } else if (n == 1) {
            return x;
        } else {
            return ((2.0 * n - 1.0) * x * legendre(n - 1, x) - (n - 1.0) * legendre(n - 2, x)) / n;
        }
    }

    public static double lambdaFun(double x, int z) {
        return Math.pow(legendre(z, x), 2);
    }

    public static double lambda(double a, double b, int z) {

        double result = 0;
        int n = 50;
        double h = (b - a) / n;

        for (int i = 0; i < n; i++) {

            double fa = a + h * i;
            double fb = a + h * (i + 1);

            result += (lambdaFun(fa, z) + lambdaFun(fb, z)) / 2 * h;

        }

        return result;
    }

    public static double funC(double x, int z) {
        return legendre(z, x) * fun(x);
    }

    public static double C(double a, double b, int z) {
        double result = 0;
        int n = 50;
        double h = (b - a) / n;

        for (int i = 0; i < n; i++) {

            double fa = a + h * i;
            double fb = a + h * (i + 1);

            result += (funC(fa, z) + funC(fb, z)) / 2 * h;

        }

        return result;
    }

    public static double compute(double a, double b, int n, double x) {
        double result = 0;
        for (int i = 0; i <= n; i++) {
            result += 1 / lambda(a, b, i) * C(a, b, i) * legendre(i, x);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(compute(-1, 1, 4, 0.25));
    }
}