
import javax.swing.JPanel;
import java.awt.*;

public class MandelbrotPanel extends JPanel {
    private MandelbrotModel mm;


    /**
     * purpose: paint the component.
     *
     * @param mm the model data
     */
    MandelbrotPanel(MandelbrotModel mm) {
        this.mm = mm;
    }

    /**
     * purpose: paint the mandelbro graphic.
     *
     * @param g the graphic
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int[][] model = mm.getModel();
        int ir = mm.getIr();
        int colour = mm.getColour();
        for (int x = 0; x < model.length; x++) {

            for (int y = 0; y < model[0].length; y++) {
// paint the point in the data coordinate one by one

                if (model[x][y] >= ir) {
//                System.out.println(x);
//                System.out.println(y);

                    g.setColor(Color.black);
                    g.drawLine(y, x, y, x); // draw one point

                } else {
                    float a = (float) model[x][y] / ir; //gradually change the color by change the parameter of color in rgb
                    if (colour == 1) {
                        g.setColor(Color.white);
                    } else if (colour == 2) {//blue
                        g.setColor(new Color(0, 0, a));
                    } else if (colour == 3) {//green
                        g.setColor(new Color(0, a, 0));
                    } else if (colour == 4) {//red
                        g.setColor(new Color(a, 0, 0));
                    }

                    g.drawLine(y, x, y, x);
                }
            }

        }
        if (!mm.isDragged()) {
            dragLine(g); // if detected that dragged the mouse draw the drag line
        }
//        saveJapnel(g);


    }
//
//    public void saveJapnel( Graphics g){
//        BufferedImage image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
//        try {
//            ImageIO.write(image, "jpeg", new File("jpanel.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * purpose: show the dragline when dragging
     *
     * @param g the graphic
     */
    private void dragLine(Graphics g) {

        int x1 = mm.getxPress();
        int x2 = mm.getxDrag();
        int y1 = mm.getyPress();
        int y2 = mm.getyDrag();
        if (mm.getColour() == 1) {
            g.setColor(Color.BLACK); // if color background is white, use black box
        } else {
            g.setColor(Color.WHITE);
        }


        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        if (x2 > x1) {
            if (y2 > y1) {
            // draw the box by paint the box points one by one
                g.drawRect(x1, y1, width, height);
            } else {
                g.setColor(Color.WHITE);
                g.drawRect(x1, y2, width, height);
            }
        } else {
            if (y2 > y1) {
                g.setColor(Color.WHITE);
                g.drawRect(x2, y1, width, height);
            } else {
                g.setColor(Color.WHITE);
                g.drawRect(x2, y2, width, height);
            }
        }
    }
}
