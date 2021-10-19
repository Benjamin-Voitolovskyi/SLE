public class SimpleIteration extends SLESolution{
    protected double[][] c;
    protected double[] d;
    protected double epsilon;
    protected double[] getNorms() {
        double[] norms = new double[3];

        norms[0] = 0.0;
        double sum;
        for (int i = 0; i < sle.getN(); ++i) {
            sum = 0.0;
            for (int j = 0; j < sle.getN(); ++j)
                sum += Math.abs(c[i][j]);
            if (sum > norms[0])
                norms[0] = sum;
        }

        norms[1] = 0.0;
        for (int j = 0; j < sle.getN(); ++j) {
            sum = 0.0;
            for (int i = 0; i < sle.getN(); ++i)
                sum += Math.abs(c[i][j]);
            if (sum > norms[1])
                norms[1] = sum;
        }

        norms[2] = 0.0;
        for (int i = 0; i < sle.getN(); ++i)
            for (int j = 0; j < sle.getN(); ++j)
                norms[2] += c[i][j] * c[i][j];
        norms[2] = Math.sqrt(norms[2]);

        return norms;
    }

    public SimpleIteration(SLE sle, double epsilon) {
        super(sle);
        this.epsilon = epsilon;

        double[][] a = sle.getA();
        double[] b = sle.getB();
        c = new double[sle.getN()][sle.getN()];
        d = new double[sle.getN()];

        for (int i = 0; i < sle.getN(); ++i) {
            d[i] = b[i] / a[i][i];
            for (int j = 0; j < sle.getN(); ++j)
                c[i][j] = (i == j) ? 0 : - a[i][j] / a[i][i];
        }
    }
    @Override
    public double[] getRoots() {
        int n = sle.getN();

        double[] norms = getNorms();
        double q = 0.0;
        for (int i = 0; i < 3; ++i)
            if (norms[i] < 1.0) {
                q = norms[i];
                break;
            }
        if (q > 0.5)
            epsilon *= (1.0 - q) / q;

        double[] x = new double[n];
        double[] prevX = new double[n];
        for (int i = 0; i < n; ++i)
            prevX[i] = 0.0;
        double max;

        do {
            max = 0.0;
            for (int i = 0; i < n; ++i) {
                x[i] = d[i];
                for (int j = 0; j < n; ++j)
                    x[i] += c[i][j] * prevX[j];
                double diff = Math.abs(x[i] - prevX[i]);
                if (diff > max)
                    max = diff;
                prevX = x;
            }
        } while (max > epsilon);

        return x;
    }
    public boolean isDiagonalPredominant() {
        double[][] a = sle.getA();
        for (int i = 0; i < sle.getN(); ++i) {
            double sum = 0;
            for (int j = 0; j < sle.getN() && j != i; ++j)
                sum += Math.abs(a[i][j]);
            if (Math.abs(a[i][i]) <= sum)
                return false;
        }
        return true;
    }
    public double[] Substitution(double[] x) {
        double[][] a = sle.getA();
        double[] approxB = new double[sle.getN()];
        for (int i = 0; i < sle.getN(); ++i) {
            approxB[i] = 0.0;
            for (int j = 0; j < sle.getN(); ++j)
                approxB[i] += a[i][j] * x[j];
        }
        return approxB;
    }
}
