import java.lang.Math;

public class Spline {

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

    public static double interpolate(double x, double[] tabX, double[] tabY, double df1, double df2) {
        double result = 0;
        int n = tabX.length;

        double[] fi = new double[n + 2];
        System.arraycopy(tabY, 0, fi, 0, n);
        fi[n] = df1;
        fi[n + 1] = df2;

        double firstEl = tabX[0];
        double lastEl = tabX[n - 1];

        double[][] F = new double[n + 2][n + 2];
        for (int i = 0; i < F.length; i++) {
            for (int j = 0; j < F.length; j++) {
                if (i < n) {
                    if (j < n - 1) {
                        F[i][j] = Math.pow(tabX[i], j);
                    }
                    if (i > 1 && j >= n - 1) {
                        F[i][j] = Math.pow(tabX[i] - tabX[j - 3], 3);
                    }
                }
            }
        }

        F[n + 1][1] = 1;
        F[n + 1][2] = 2 * lastEl;
        F[n + 1][3] = 3 * Math.pow(lastEl, 2);

        for (int i = 1; i < F.length; i++) {
            if (i < 4) {
                F[i][n + 1] = 0;
                F[n][i] = i * Math.pow(firstEl, i - 1);
            } else {
                F[n + 1][i] = 3 * Math.pow(lastEl - tabX[i - 3], 2);
            }
        }

        double[] results = Gauss(F, fi);

        for (int i = 0; i < results.length - 1; i++) {
            if (i < 4) {
                result += results[i] * Math.pow(x, i);
            } else {
                result += results[i] * Math.pow(x - tabX[i - 3], 3);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        double[] tabX = {-4,-2,0,2,4};
        double[] tabY = {876,52,4,108,1276};
        double x = 1;
        double df1 = -902;
        double df2 = 1194;

        System.out.println(interpolate(x, tabX, tabY, df1, df2));
    }
}
