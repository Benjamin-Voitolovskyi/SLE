public class Comparison {
    public static boolean areEqual(double d1, double d2) {
        double eps = 1e-9;
        if(Math.abs(d2 - d1) < eps)
            return true;
        return false;
    }
}
