
public class Mandelbrot {
    /**
     * purpose: main class to run.
     *
     *
     * @author: 170011304
     *
     *
     * @param args input arguments:do not need
     */
    public static void main(String[] args) {

        MandelbrotModel model = new MandelbrotModel();
        MandelbrotDelegate delegate = new MandelbrotDelegate(model);
        //pass the model data to delegate, if observed, create image on Jpanel
    }
}
