public class Lagrange {

    public static double interpolate(double x, double[] tabX, double[] tabY) {

        int n = tabX.length;
        double result = 0;

        for (int i = 0; i < n; i++) {
            double temp = tabY[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    temp *= (x - tabX[j]) / (tabX[i] - tabX[j]);
                }
            }
            result += temp;
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
