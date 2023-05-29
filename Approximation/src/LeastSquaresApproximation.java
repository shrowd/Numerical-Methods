import java.lang.Math;

public class LeastSquaresApproximation {
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

    public static double fun(double x) {
        return Math.sqrt(8 * Math.pow(x, 2) + 3);
    }

    public static double sumS(double[] tabX, int k) {
        double result = 0;
        for (double i : tabX) {
            result += Math.pow(i, k);
        }
        return result;
    }

    public static double sumT(double[] tabX, double[] tabY, int k) {
        double result = 0;
        for (int i = 0; i < tabX.length; i++) {
            result += Math.pow(tabX[i], k) * tabY[i];
        }
        return result;
    }

    public static double compute(double[] tabX, int m, double x) {

        double[][] A = new double[m + 1][m + 1];
        double[] B = new double[m + 1];

        double[] tabY = new double[tabX.length];
        for (int i = 0; i < tabX.length; i++) {
            tabY[i] = fun(tabX[i]);
        }

        for (int i = 0; i <= m; i++) {
            B[i] = sumT(tabX, tabY, i);
            for (int j = 0; j <= m; j++) {
                A[i][j] = sumS(tabX, i + j);
            }
        }

        double[] temp = Gauss(A, B);
        double result = 0;

        for (int i = 0; i <= m; i++) {
            result += Math.pow(x, i) * temp[i];
        }

        return result;
    }

    public static void main(String[] args) {
        double[] tabX = new double[]{-1, -0.5, 0, 0.5, 1};
        System.out.println("Najmniejsze kwadraty: " + compute(tabX, 5, 0.25));
    }
}