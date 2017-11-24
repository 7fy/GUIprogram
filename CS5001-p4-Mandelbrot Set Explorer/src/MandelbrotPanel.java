
import javax.swing.JPanel;
import java.awt.*;

public class MandelbrotPanel extends JPanel {
    private MandelbrotModel mm;


    /**
     * @param mm
     */
    MandelbrotPanel(MandelbrotModel mm) {
        this.mm = mm;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int[][] model = mm.getModel();
        int ir = mm.getIr();
        int colour = mm.getColour();
        for (int x = 0; x < model.length; x++) {

            for (int y = 0; y < model[0].length; y++) {


                if (model[x][y] >= ir) {
//                System.out.println(x);
//                System.out.println(y);

                    g.setColor(Color.black);
                    g.drawLine(y, x, y, x);//画一个点。

                } else {
                    float a = (float) model[x][y] / ir;
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
            dragLine(g);
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


    private void dragLine(Graphics g) {

        int x1 = mm.getxPress();
        int x2 = mm.getxDrag();
        int y1 = mm.getyPress();
        int y2 = mm.getyDrag();
        if (mm.getColour() == 1) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }


        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        if (x2 > x1) {
            if (y2 > y1) {

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
