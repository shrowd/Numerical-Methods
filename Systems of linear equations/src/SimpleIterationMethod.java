import java.util.Arrays;

public class SimpleIterationMethod {
    public static double[] compute(double[][] A, double[] B, double e) {

        int n = B.length;
        double[] result = new double[n];
        double[] prevResult = new double[n];
        int iterations = 0;

        Arrays.fill(result, 0);

        do {
            iterations++;

            System.arraycopy(result, 0, prevResult, 0, n);

            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma += A[i][j] * result[j];
                    }
                }
                result[i] = (B[i] - suma) / A[i][i];
            }

        } while (stop(result, prevResult) > e);

        System.out.println("Liczba iteracji: " + iterations);
        return result;
    }

    public static double stop(double[] v1, double[] v2) {
        double sum = 0;
        for (int i = 0; i < v1.length; i++) {
            double diff = v1[i] - v2[i];
            sum += diff * diff;
        }
        return sum;
    }

    public static void main(String[] args) {
        double[][] A = {
                {3, 1, 2},
                {1, -4, 1},
                {1, 2, 3}
        };
        double[] B = {5, -7, 2};
        double e = 0.00001;

        double[] result = compute(A, B, e);

        System.out.println("Wynik: " + Arrays.toString(result));
    }
}