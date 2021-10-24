public class Seidel{
    void SeidelMethod(double[][] A) {
        double x = 0, y = 0, z = 0, d = 0, x1 = 0, y1 = 0, z1 = 0, d1 = 0;
        double epsilon = 0.0001;
        double termination = 2 * epsilon;
        for (int i = 1; termination >= epsilon; i++)
        {
            x1 = (A[0][1] * y1 + A[0][2] * z1 + A[0][3] * d1 + A[0][4]);
            y1 = (A[1][2] * z1 + A[1][3] * d1 + A[1][4]) / A[1][1];
            z1 = (A[2][0] * x1 + A[2][1] * y1 + A[2][3] * d1 + A[2][4] )/ A[2][2];
            d1 = (A[3][0] * x1 + A[3][1] * y1 + A[3][2] * z1 + A[3][4]) / A[3][3];

            termination = Math.max(Math.max(Math.abs(x1 - x), Math.abs(y1 - y)), Math.max(Math.abs(z1 - z), Math.abs(d1 - d)));
            x = x1; y = y1; z = z1; d = d1;

            System.out.println(
                    "k[" + i + "]: " +
                            "x1 = "  +  Math.round(x * 100000.0) / 100000.0 +
                            "  x2 = "  +  Math.round(y * 100000.0) / 100000.0 +
                            "  x3 = "  +  Math.round(z * 100000.0) / 100000.0 +
                            "  x4 = " +  Math.round(d * 100000.0) / 100000.0 +
                            "  max e = " + termination);
        }
        System.out.println("Result:");
        System.out.println(
                "x1 = " +  Math.round(x * 10000.0) / 10000.0 +
                        "   x2 = " +  Math.round(y * 10000.0) / 10000.0 +
                        "   x3 = " +  Math.round(z * 10000.0) / 10000.0 +
                        "   x4 = " +  Math.round(d * 10000.0) / 10000.0);
    }
}
