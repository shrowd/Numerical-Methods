import java.lang.Math;

public class LeastSquaresApproximation {
    public static double fun(double x) {
        return Math.sqrt(8 * Math.pow(x, 2) + 3);
    }

    public static double integralFun(double x, int... args) {

        double result = 1;

        if (args.length == 1) {
            result = Math.pow(x, args[0]) * fun(x);
        } else if (args.length == 2) {
            result = Math.pow(x, args[0]) * Math.pow(x, args[1]) * 1;
        }

        return result;
    }

    public static double integrate(double a, double b, int... args) {

        double result = 0;
        int n = 50;
        double h = (b - a) / n;

        if (args.length == 1) {
            for (int i = 0; i < n; i++) {

                double fa = a + h * i;
                double fb = a + h * (i + 1);

                result += (integralFun(fa, args[0]) + integralFun(fb, args[0])) / 2 * h;
            }
        } else if (args.length == 2) {
            for (int i = 0; i < n; i++) {

                double fa = a + h * i;
                double fb = a + h * (i + 1);

                result += (integralFun(fa, args[0], args[1]) + integralFun(fb, args[0], args[1])) / 2 * h;
            }
        }

        return result;
    }

    public static double[] Gauss(double[][] A, double[] B) {
        int n = A.length;

        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[max][i])) {
                    max = j;
                }
            }

            double[] tempRow = A[i];
            A[i] = A[max];
            A[max] = tempRow;

            double tempVal = B[i];
            B[i] = B[max];
            B[max] = tempVal;

            for (int j = i + 1; j < n; j++) {
                double factor = A[j][i] / A[i][i];
                B[j] -= factor * B[i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= factor * A[i][k];
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (B[i] - sum) / A[i][i];
        }

        return x;
    }

    public static double compute(double a, double b, int n, double x) {

        double[][] A = new double[n + 1][n + 1];
        double[] B = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            B[i] = integrate(a, b, i);
            for (int j = 0; j <= n; j++) {
                A[i][j] = integrate(a, b, i, j);
            }
        }

        double[] temp = Gauss(A, B);
        double result = 0;

        for (int i = 0; i < temp.length; i++) {
            result += temp[i] * Math.pow(x, i);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(compute(-1, 1, 4, 0.25));
    }
}