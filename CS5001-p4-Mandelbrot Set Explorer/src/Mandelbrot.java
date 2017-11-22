
public class Mandelbrot {
    protected static final double INITIAL_MIN_REAL = -2.0;
    protected static final double INITIAL_MAX_REAL = 0.7;
    protected static final double INITIAL_MIN_IMAGINARY = -1.25;
    protected static final double INITIAL_MAX_IMAGINARY = 1.25;
    protected static final int INITIAL_MAX_ITERATIONS = 50;
    // Default parameter values
    protected static final double DEFAULT_RADIUS_SQUARED = 4.0;

    public static void main(String[] args) {

        MandelbrotCalculator mandelCalc = new MandelbrotCalculator();
        int[][] madelbrotData = mandelCalc.calcMandelbrotSet(20, 25,
                INITIAL_MIN_REAL, INITIAL_MAX_REAL, INITIAL_MIN_IMAGINARY, INITIAL_MAX_IMAGINARY,
                INITIAL_MAX_ITERATIONS, DEFAULT_RADIUS_SQUARED);
        MandelbrotModel model = new MandelbrotModel();
        MandelbrotDelegate delegate = new MandelbrotDelegate(model);

    }
}
