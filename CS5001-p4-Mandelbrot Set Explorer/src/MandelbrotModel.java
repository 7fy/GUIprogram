import java.util.Observable;

public class MandelbrotModel extends Observable {
    private int xR ;
    private int yR ;
    private int[][] model;
    private double minR;
    private double maxR;
    private double minI;
    private double maxI;
    private int ir;
    private double rs;
    private int colour;
    private boolean dragged;
    public int xPress;
    public int xDrag;
    public int yPress;
    public int yDrag;


    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }

    public boolean isDragged() {
        return dragged;
    }


    public MandelbrotModel() {
        getDefault();
        MandelbrotCalculator mc = new MandelbrotCalculator();
        this.model = mc.calcMandelbrotSet(xR, yR, minR, maxR, minI, maxI, ir, rs);

    }

    public Object[] getData() {

        Object[] data = new Object[8];
        data[0] = xR;
        data[1] = yR;
        data[2] = minR;
        data[3] = maxR;
        data[4] = minI;
        data[5] = maxI;
        data[6] = ir;
        data[7] = colour;
        return data;
    }




    public void setData(Object[] data){
        this.xR = (int) data[0];
        this.yR = (int) data[1];
        this.minR = (double) data[2];
        this.maxR = (double) data[3];
        this.minI = (double) data[4];
        this.maxI = (double) data[5];
        this.ir = (int) data[6];
        this.colour = (int) data[7];

    }

    public void createData() {
        MandelbrotCalculator mc = new MandelbrotCalculator();
        System.out.println("xr"+xR+"yR"+yR);
        this.model = mc.calcMandelbrotSet(xR, yR, minR, maxR, minI, maxI, ir, rs);
    }
    //regenerate data after the parameter changed

    public void setIr(int ir) {
        this.ir = ir;
    }


    public int getColour() {
        return colour;
    }


    public void setColour(int colour) {
        this.colour = colour;
    }

    public int[][] getModel() {
        return model;
    }

    public int getIr() {
//        System.out.println(ir);

        return ir;
    }

    public int getxDrag() {
        return xDrag;
    }

    public int getyPress() {
        return yPress;
    }

    public int getyDrag() {
        return yDrag;
    }

    public int getxPress() {
        return xPress;
    }

    public void setYR(int yR) {
        this.yR = yR;
    }

    public void setXR(int xR) {
        this.xR = xR;
    }

    public void setMinR(double minR) {
        this.minR = minR;
    }

    public void setMinI(double minI) {
        this.minI = minI;
    }

    public void setMaxR(double maxR) {
        this.maxR = maxR;
    }

    public void setMaxI(double maxI) {
        this.maxI = maxI;
    }


    public void setxDrag(int xDrag) {
        this.xDrag = xDrag;
    }

    public void setxPress(int xPress) {
        this.xPress = xPress;
    }

    public void setyDrag(int yDrag) {
        this.yDrag = yDrag;
    }

    public void setyPress(int yPress) {
        this.yPress = yPress;
    }

    public double getMaxI() {
        return maxI;
    }


    public double getMinI() {
        return minI;
    }

    public double getMinR() {
        return minR;
    }

    public double getMaxR() {
        return maxR;
    }

    public int getXR() {
        return xR;
    }

    public int getYR() {
        return yR;
    }


    public void getDefault() {
        this.minI = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
        this.maxI = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
        this.minR = MandelbrotCalculator.INITIAL_MIN_REAL;
        this.maxR = MandelbrotCalculator.INITIAL_MAX_REAL;
        this.ir = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
        this.rs = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
        this.xR = 800;
        this.yR = 800;
        this.colour = 1;
    }


}
//    protected static final double INITIAL_MIN_REAL = -2.0;
//    protected static final double INITIAL_MAX_REAL = 0.7;
//    protected static final double INITIAL_MIN_IMAGINARY = -1.25;
//    protected static final double INITIAL_MAX_IMAGINARY = 1.25;
//    protected static final int INITIAL_MAX_ITERATIONS = 50;