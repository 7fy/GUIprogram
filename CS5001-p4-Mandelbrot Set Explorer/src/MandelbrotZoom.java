
import static java.lang.StrictMath.abs;

public class MandelbrotZoom {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double minR;
    private double maxR;
    private double minI;
    private double maxI;
    private int xR;
    private int yR;


    public int getX1(){
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }




    public void setX1(int x1) {
        this.x1 = x1;
    }


    public void setX2(int x2) {
        this.x2 = x2;
    }


    public void setY1(int y1) {
        this.y1 = y1;
    }


    public void setY2(int y2) {
        this.y2 = y2;
    }


    public void setMaxI(double maxI) {
        this.maxI = maxI;
    }

    public void setMaxR(double maxR) {
        this.maxR = maxR;
    }


    public void setMinI(double minI) {
        this.minI = minI;
    }


    public void setMinR(double minR) {
        this.minR = minR;
    }



    public void setXR(int xR) {
        this.xR = xR;
    }


    public void setYR(int yR) {
        this.yR = yR;
    }

    public double reCalcMinR(int x1 ){
        double recalcData = minR+(x1*(maxR-minR))/xR ;
        System.out.println("minR"+recalcData);

        return(recalcData);

    }

    public double reCalcMaxR(int x2 ){
        double recalcData = minR + (x2*(maxR-minR))/xR;


        System.out.println("maxR" +(recalcData));

        return(recalcData);
    }

    public double reCalcMinI(int y1){
        double recalcData = minI+(y1*(maxI-minI))/yR ;
        //if divided first wrong
        System.out.println("minI"+recalcData);

        return (recalcData);
    }

    public double reCalcMaxI(int y2){
        double recalcData =minI + (y2*(maxI-minI))/yR;
        System.out.println("maxI"+recalcData);

        return (recalcData);
    }

    public int reCalcXR (int x1,int x2, int y1, int y2){

        xR = (abs(x2-x1)*800)/abs(y2-y1);
        System.out.println("x1R"+xR);

        return xR;
    }

    public int reCalcYR (int x1,int x2, int y1, int y2){




        yR = (abs(y2-y1)*800)/abs(x2-x1);
        System.out.println("y1R"+yR);
        return yR;

    }
}


