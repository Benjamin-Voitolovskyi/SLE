public class Gauss extends SLESolution{
    private int findMaxDiagCoef(int pos, double[][] matrix) {
        int max = pos;
        for (int i = pos + 1; i < sle.getM(); ++i)
            if(Math.abs(matrix[i][pos]) > Math.abs(matrix[max][pos]))
                max = i;
        return max;
    }

    public Gauss(SLE sle) {
        super(sle);
    }
    @Override
    public double[] getRoots() throws Exception{
        int m = sle.getM();
        int n = sle.getN();

        double[][] augmentedA = sle.getAugmentedMatrix();
        double[] x = new double[n];

        for (int k = 0; k < Math.min(m, n); ++k) {
            int maxPos = findMaxDiagCoef(k, augmentedA);
            if (maxPos != k) {
                double[] maxRow = augmentedA[maxPos];
                augmentedA[maxPos] = augmentedA[k];
                augmentedA[k] = maxRow;
            }

            double main = augmentedA[k][k];
            if(!Comparison.areEqual(main, 0.0)) {
                for (int i = k + 1; i < m; ++i) {
                    double mik = -augmentedA[i][k] / main;
                    for (int j = k + 1; j < n + 1; ++j)
                        augmentedA[i][j] += mik * augmentedA[k][j];
                    augmentedA[i][k] = 0.0;
                }

                for (int i = k; i < n + 1; ++i)
                    augmentedA[k][i] /= main;
            }
        }

        boolean isCompatible = true;
        for (int i = m - 1; i > 0; --i) {
            boolean isZeroRow = true;
            for (int j = 0; j < n; ++j)
                if (!Comparison.areEqual(augmentedA[i][j], 0.0)) {
                    isZeroRow = false;
                    break;
                }
            if (isZeroRow && !Comparison.areEqual(augmentedA[i][n], 0.0)) {
                isCompatible = false;
                break;
            }
        }

        if (!isCompatible)
            throw new Exception("The system is not compatible!");

        int rank = 0;
        for (int i = 0; i < Math.min(m, n); ++i)
            if (augmentedA[i][i] != 0.0)
                ++rank;

        if (n > m || rank < n)
            throw new Exception("The system has unlimited amount of roots!");

        x[n - 1] = augmentedA[n - 1][n] / augmentedA[n - 1][n - 1];
        for (int k = n - 2; k >= 0; --k) {
            x[k] = augmentedA[k][n];
            for (int j = k + 1; j < n; ++j)
                x[k] -= augmentedA[k][j] * x[j];
            x[k] /= augmentedA[k][k];
        }

        return x;
    }
}
