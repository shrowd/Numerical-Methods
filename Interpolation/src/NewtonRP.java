import java.lang.Math;

public class NewtonRP {

    public static int factorial(int n) {
        int result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    public static double[] compute(double[] x, double[] y) {
        int n = x.length;
        double[][] tab = new double[n][n];
        double[] f = new double[n];
        double h = x[n - 1] - x[n - 2];

        for (int i = 0; i < n; i++) {
            tab[i][0] = y[i];
        }

        for (int j = 1; j <= n; j++) {
            for (int i = j; i < n; i++) {
                tab[i][j] = (tab[i][j - 1] - tab[i - 1][j - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            f[i] = tab[i][i];
        }

        for(int i = 0; i < n; i++){
            f[i] = f[i] / (factorial(i) * Math.pow(h,i));
        }

        return f;
    }

    public static double interpolate(double x, double[] tabX, double[] tabY) {
        int n = tabX.length;
        double[] f = compute(tabX, tabY);
        double result = f[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            result = result * (x - tabX[i]) + f[i];
        }
        return result;
    }

    public static void main(String[] args){
        double[] tabX = {-4,-2,0,2,4};
        double[] tabY = {876,52,4,108,1276};
        double x = 1;

        System.out.println(interpolate(x, tabX, tabY));
    }
}
