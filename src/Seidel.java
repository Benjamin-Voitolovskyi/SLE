public class Seidel extends SimpleIteration{
    public Seidel(SLE sle, double epsilon) {
        super(sle, epsilon);
    }
    public Seidel(SimpleIteration simpleIteration) {
        super(simpleIteration.sle, simpleIteration.epsilon);
    }
    @Override
    public double[] getRoots() {
        //implement this method!
        return super.getRoots();
    }
    //write everything else you need in this class
}
