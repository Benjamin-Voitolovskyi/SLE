import java.util.Scanner;

public class Program {
    private static void printVector(double[] vec) {
        for (double v : vec) System.out.print(v + " ");
        System.out.println();
    }
    private static double[][] inputMatrix(int m, int n, Scanner in) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                a[i][j] = in.nextDouble();
        return a;
    }
    private static double[] inputVector(int m, Scanner in) {
        double[] b = new double[m];
        for (int i = 0; i < m; ++i)
            b[i] = in.nextDouble();
        return b;
    }
    public static void main(String[] args) {
        System.out.println("Accurate methods:");
        int m, n;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("(m > 1 && n > 1) m, n = ");
            m = in.nextInt();
            n = in.nextInt();
        } while (m <= 1 || n <= 1);

        System.out.println("Input matrix A:");
        double[][] a = inputMatrix(m, n, in);
        System.out.println("Input vector b:");
        double[] b = inputVector(m, in);

        Gauss gauss = new Gauss(new SLE(a, b));
        double[] x;
        try {
            x = gauss.getRoots();
            System.out.println("Roots:");
            System.out.println("Gauss method:");
            for (int i = 0; i < n; ++i) {
                System.out.format("%.0f ", x[i]);
            }
            System.out.println();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        //If you write accurate methods, test them here.
        //For an accurate method create a separate class
        //which extents SLESolution.

        System.out.println("Approximate methods:");
        do {
            System.out.print("(n > 1) n = ");
            n = in.nextInt();
        } while (n <= 1);

        SimpleIteration simpleIteration;
        System.out.print("epsilon = ");
        double epsilon = in.nextDouble();
        System.out.println("Input vector b:");
        b = inputVector(n, in);
        do {
            System.out.println("Input matrix A with diagonal predominance:");
            a = inputMatrix(n, n, in);
            simpleIteration = new SimpleIteration(new SLE(a, b), epsilon);
        } while (!simpleIteration.isDiagonalPredominant());

        System.out.println("Approximate roots:");
        x = simpleIteration.getRoots();
        printVector(x);
        System.out.println("Compare approximate b with actual b:");
        printVector(simpleIteration.Substitution(x));
        printVector(b);

        //Test Seidel's method here.
        double[][] A = {
                { 0, 0.17, -0.31, 0.16, -1.2 },
                { 0, 0.82, 0.43, -0.08, 0.38 },
                { 0.22, 0.18, 0.75, 0.07, 0.48 },
                { 0.08, 0.07, 0.71, 0.96, -1.24 },
        };
        Seidel seidel = new Seidel();;
        seidel.SeidelMethod(A);
    }
}
