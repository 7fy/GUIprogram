import java.util.Observable;

/**
 * purpose: save the model information and transfer data in model.
 */
public class MandelbrotModel extends Observable {
    private int xR;
    private int yR;
    private int[][] model;
    private double minR;
    private double maxR;
    private double minI;
    private double maxI;
    private int ir;
    private double rs;
    private int colour;
    private boolean dragged;
    private int xPress;
    private int xDrag;
    private int yPress;
    private int yDrag;


    /**
     * purpose: create the default model.
     */
    public MandelbrotModel() {
        getDefault();
        MandelbrotCalculator mc = new MandelbrotCalculator();
        this.model = mc.calcMandelbrotSet(xR, yR, minR, maxR, minI, maxI, ir, rs);

    }

    /**
     * purpose: recreate model using current parameters and conditions.
     */
    public void createModel() {
        MandelbrotCalculator mc = new MandelbrotCalculator();
        System.out.println("xr" + xR + "yR" + yR);
        this.model = mc.calcMandelbrotSet(xR, yR, minR, maxR, minI, maxI, ir, rs);
    }
    //regenerate data after the parameter changed

    /**
     * purpose:output the information of the model.
     *
     * @return the information of current model
     */
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


    /**
     * purpose: reset the information of the model.
     *
     * @param data the information of the model
     */
    public void setData(Object[] data) {
        this.xR = (int) data[0];
        this.yR = (int) data[1];
        this.minR = (double) data[2];
        this.maxR = (double) data[3];
        this.minI = (double) data[4];
        this.maxI = (double) data[5];
        this.ir = (int) data[6];
        this.colour = (int) data[7];
        notifyObservers();

    }
    /**
     * purpose: reset the dragged boolean.
     *
     * @param dragged judge that whether the mouse has dragged
     */
    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }


    /**
     * purpose: output the information of whether has dragged.
     *
     * @return whether the mouse has dragged
     */
    public boolean isDragged() {
        return dragged;
    }

    /**
     * purpose: set the max iterations parameter.
     * @param ir the max iterations parameter
     */
    public void setIr(int ir) {
        this.ir = ir;
    }

    /**
     * purpose: get the value of current max iterations parameter.
     * @return the value of current max iterations parameter.
     */
    public int getIr() {

        return ir;
    }

    /**
     * purpose: get the parameter of image colour.
     * @return the parameter of image colour.
     */
    public int getColour() {
        return colour;
    }

    /**
     * purpose: set the colour.
     * @param colour the parameter of image's colour.
     */
    public void setColour(int colour) {
        this.colour = colour;
    }


    /**
     * purpose: get current model data.
     * @return current model data
     */
    public int[][] getModel() {
        return model;
    }

    /**
     * purpose: get the value of vector x when dragging mouse.
     * @return the value of vector x when dragging mouse.
     */
    public int getxDrag() {
        return xDrag;
    }



    /**
     * purpose: get the value of vector y when dragging mouse.
     * @return the value of vector y when dragging mouse.
     */
    public int getyDrag() {
        return yDrag;
    }


    /**
     * purpose: get the value of vector x when pressing mouse.
     * @return the value of vector x when pressing mouse.
     */
    public int getxPress() {
        return xPress;
    }


    /**
     * purpose: get the value of vector y when pressing mouse.
     * @return the value of vector y when pressing mouse.
     */
    public int getyPress() {
        return yPress;
    }

    /**
     * purpose:reset the xDrag
     * @param xDrag
     */
    public void setxDrag(int xDrag) {
        this.xDrag = xDrag;
    }

    /**
     * purpose:reset parameter
     * @param xPress
     */
    public void setxPress(int xPress) {
        this.xPress = xPress;
    }

    /**
     * purpose:reset parameter
     * @param yDrag
     */
    public void setyDrag(int yDrag) {
        this.yDrag = yDrag;
    }

    /**
     * purpose:reset parameter
     * @param yPress
     */
    public void setyPress(int yPress) {
        this.yPress = yPress;
    }


    /**
     * purpose:reset parameter
     * @param yR
     */
    public void setYR(int yR) {
        this.yR = yR;
    }

    /**
     * purpose:reset parameter
     * @param xR
     */
    public void setXR(int xR) {
        this.xR = xR;
    }

    /**
     * purpose:reset parameter
     * @param minR
     */
    public void setMinR(double minR) {
        this.minR = minR;
    }

    /**
     * purpose:reset parameter
     * @param minI
     */
    public void setMinI(double minI) {
        this.minI = minI;
    }

    /**
     * purpose:reset parameter
     * @param maxR
     */
    public void setMaxR(double maxR) {
        this.maxR = maxR;
    }

    /**
     * purpose:reset parameter
     * @param maxI
     */
    public void setMaxI(double maxI) {
        this.maxI = maxI;
    }


    /**
     * purpose:get parameter
     * @return
     */
    public double getMaxI() {
        return maxI;
    }

    /**
     * purpose:get parameter
     * @return
     */
    public double getMinI() {
        return minI;
    }

    /**
     * purpose:get parameter
     * @return
     */
    public double getMinR() {
        return minR;
    }

    /**
     * purpose:get parameter
     * @return
     */
    public double getMaxR() {
        return maxR;
    }

    /**
     * purpose:get parameter
     * @return
     */
    public int getXR() {
        return xR;
    }

    /**
     * purpose:get parameter
     * @return
     */
    public int getYR() {
        return yR;
    }

    /**
     * purpose: reset the default parameter
     */
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