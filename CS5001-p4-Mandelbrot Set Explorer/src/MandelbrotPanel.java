import javax.swing.JPanel;
import java.awt.*;

public class MandelbrotPanel extends JPanel {
    MandelbrotModel mm;

    public MandelbrotPanel(MandelbrotModel mm) {
        this.mm = mm;
    }

    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int[][] model = mm.getModel();
    int ir = mm.getIr();

    for(int x =0; x<model.length;x++){

        for(int y=0;y<model.length;y++){
            if (model[x][y]>=ir){
//                System.out.println(x);
//                System.out.println(y);

                g.setColor(Color.black);
                g.drawLine(y,x,y,x);//画一个点。
            }else{
//                System.out.println("sds"+x);
//                System.out.println(y);
                g.setColor(Color.white);
                g.drawLine(y,x,y,x);
            }
        }

    }

    }

}
