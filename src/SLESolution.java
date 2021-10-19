public abstract class SLESolution {
    protected SLE sle;

    public SLESolution(SLE sle) {
        this.sle = sle;
    }
    public abstract double[] getRoots() throws Exception;
}
