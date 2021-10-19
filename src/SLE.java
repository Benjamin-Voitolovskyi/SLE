public class SLE {
    private double[][] a;
    private double[] b;
    private int n;
    private int m;


    public double[][] getAugmentedMatrix() {
        double[][] augmentedA = new double[m][n + 1];
        for(int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                augmentedA[i][j] = a[i][j];
            augmentedA[i][n] = b[i];
        }
        return augmentedA;
    }
    public SLE(double[][] a, double[] b) {
        this.a = a;
        this.b = b;
        m = a.length;
        n = a[0].length;
    }
    public int getN() {
        return n;
    }
    public int getM() {
        return m;
    }
    public double[][] getA() {
        return a;
    }

    public double[] getB() {
        return b;
    }
}
