import java.util.Observable;

public class MandelbrotModel extends Observable{
    private static final int FRAME_HEIGHT = 800;
    private static final int FRAME_WIDTH = 800;
    private int[][] model;
    private double minR;
    private double maxR;
    private double minI;
    private double maxI;
    private int ir;
    private double rs;




    public MandelbrotModel(){
        getDefault();
        MandelbrotCalculator mc = new MandelbrotCalculator();
        this.model = mc.calcMandelbrotSet( FRAME_HEIGHT, FRAME_WIDTH,minR, maxR,minI, maxI, ir,rs);

    }

    public int[][] getModel() {

        return model;
    }

    public void createData( ){
        MandelbrotCalculator mc = new MandelbrotCalculator();
        this.model = mc.calcMandelbrotSet( FRAME_HEIGHT, FRAME_WIDTH,minR, maxR,minI, maxI, ir,rs);
    }
    //regenerate data after the parameter changed

    public void setIr(int ir) {
        this.ir = ir;
    }


    public int getIr() {
//        System.out.println(ir);

        return ir;
    }

    public void getDefault(){
        this.minI = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
        this.maxI = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
        this.minR = MandelbrotCalculator.INITIAL_MIN_REAL;
        this.maxR = MandelbrotCalculator.INITIAL_MAX_REAL;
        this.ir = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
        this.rs = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
    }


}
//    protected static final double INITIAL_MIN_REAL = -2.0;
//    protected static final double INITIAL_MAX_REAL = 0.7;
//    protected static final double INITIAL_MIN_IMAGINARY = -1.25;
//    protected static final double INITIAL_MAX_IMAGINARY = 1.25;
//    protected static final int INITIAL_MAX_ITERATIONS = 50;