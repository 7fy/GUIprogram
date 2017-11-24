import static java.lang.StrictMath.abs;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


/**
 * The SimpleGuiDelegate class whose purpose is to render relevant state information stored in the model and make changes to the model state based on user events.
 * <p>
 * This class uses Swing to display the model state when the model changes. This is the view aspect of the delegate class.
 * It also listens for user input events (in the listeners defined below), translates these to appropriate calls to methods
 * defined in the model class so as to make changes to the model. This is the controller aspect of the delegate class.
 * The class implements Observer in order to permit it to be added as an observer of the model class.
 * When the model calls notifyObservers() (after executing setChanged())
 * the update(...) method below is called in order to update the view of the model.
 *
 * @author jonl
 */
public class MandelbrotDelegate implements Observer {

    private static final int FRAME_HEIGHT = 1000;
    private static final int FRAME_WIDTH = 1000;
    private static final int TEXT_WIDTH = 10;

    private JFrame mainFrame;

    private JToolBar toolbar;
    private JTextField inputField;


    private int xDrag;
    private int yDrag;
    private int xPress;
    private int yPress;

    private MandelbrotModel model;

    private MandelbrotPanel mandelbrotPanel;

    private MandelbrotRecord record = new MandelbrotRecord();

    /**
     *
     * @param model
     */
    public MandelbrotDelegate(MandelbrotModel model) {

        this.model = model;
//        record.pushUndo(model.getData());
        this.mainFrame = new JFrame();  // set up the main frame for this GUI
        toolbar = new JToolBar();
        inputField = new JTextField(TEXT_WIDTH);
        setupComponents();

        // add the delegate UI component as an observer of the model
        // so as to detect changes in the model and update the GUI view accordingly
        model.addObserver(this);

    }


    /**
     *
     */
    private void setupToolbar() {
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem saveImage = new JMenuItem("save Image");

        file.add(save);
        file.add(load);
        file.add(saveImage);

        menu.add(file);


        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                File workingDirectory = new File(System.getProperty("user.dir"));
                fc.setCurrentDirectory(workingDirectory);
                int returnVal = fc.showOpenDialog(fc);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {


                        FileInputStream fi = new FileInputStream(file);
                        ObjectInputStream oi = new ObjectInputStream(fi);

                        model.setData((Object[]) oi.readObject());
                        model.createModel();
                        mandelbrotPanel.repaint();

                        oi.close();
                        fi.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        save.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
                                   public void actionPerformed(ActionEvent e) {
                                       JFileChooser jf = new JFileChooser();
                                       File workingDirectory = new File(System.getProperty("user.dir"));

                                       jf.setCurrentDirectory(workingDirectory);
                                       int returnVal = jf.showSaveDialog(jf);
                                       if (returnVal == JFileChooser.APPROVE_OPTION) {
                                           File file = jf.getSelectedFile();
                                           try {
                                               System.out.println("File is " + file.toString());
                                               FileOutputStream f = new FileOutputStream(file);
                                               ObjectOutputStream o = new ObjectOutputStream(f);
                                               o.writeObject(model.getData());
                                               o.close();
                                               f.close();
                                           } catch (FileNotFoundException e1) {
                                               e1.printStackTrace();
                                           } catch (IOException e1) {
                                               e1.printStackTrace();
                                           }
                                       }
                                   }
                               });

//        saveImage.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//
//            }
//        });


        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                if (record.undo.isEmpty()) {
                    System.out.println("fuck you");

                } else {

                    record.pushRedo(model.getData());
                    Object[] data = record.popUndo();
                    model.setData(data);
                    model.createModel();
                    mandelbrotPanel.repaint();
                }
            }
        });

//        inputField.addKeyListener(new KeyListener(){        // to translate key event for the text filed into appropriate model method call
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode() == KeyEvent.VK_ENTER){
//                    model.setIr(Integer.parseInt(inputField.getText()));    // tell model to add text entered by user
//                    inputField.setText("");
//// clear the input box in the GUI view
//                }
//            }
//            public void keyReleased(KeyEvent e) {
//            }
//            public void keyTyped(KeyEvent e) {
//            }
//        });

        JButton redo = new JButton("Redo");
        redo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (record.redo.isEmpty()) {
                    System.out.println("fuck you again");
                } else {
                    // should  call method in model class if you want it to affect
                    record.pushUndo(model.getData());
                    Object[] data = record.popRedo();
                    model.setData(data);
                    model.createModel();
                    mandelbrotPanel.repaint();

                }
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());

                model.getDefault();
                model.createModel();
                mandelbrotPanel.repaint();

                // should  call method in model class if you want it to affect model
            }
        });

        JButton blue = new JButton("blue");
        blue.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());
                model.setColour(2);
                mandelbrotPanel.repaint();

            }
        });

        JButton green = new JButton("green");
        green.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());
                model.setColour(3);
                mandelbrotPanel.repaint();

            }
        });

        JButton red = new JButton("red");
        red.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());
                model.setColour(4);
                mandelbrotPanel.repaint();

            }
        });


        JButton white = new JButton("white");
        white.addActionListener(new ActionListener() {     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());
                model.setColour(1);
                mandelbrotPanel.repaint();

            }
        });

        JLabel label = new JLabel("Change precision");

        JLabel label1 = new JLabel("  Colour");


        JButton apply_button = new JButton("Apply");       // to translate event for this button into appropriate model method call
        apply_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                record.pushUndo(model.getData());

                model.setIr(Integer.parseInt(inputField.getText()));        // same as when user presses carriage return key, tell model to add text entered by user
                model.createModel();
                mandelbrotPanel.repaint();
                inputField.setText("");


            }
        });

        // add buttons, label, and textfield to the toolbar
        mainFrame.setJMenuBar(menu);

        toolbar.add(undo);
        toolbar.add(redo);
        toolbar.add(reset);
        toolbar.add(label1);

        toolbar.add(blue);
        toolbar.add(green);
        toolbar.add(red);
        toolbar.add(white);


        toolbar.add(label);
        toolbar.add(inputField);
        toolbar.add(apply_button);
        // add toolbar to north of main frame
        mainFrame.add(toolbar, BorderLayout.NORTH);
    }

    private void setupComponents() {
        mandelbrotPanel = new MandelbrotPanel(model);
        MandelbrotZoom mandelbrotZoom = new MandelbrotZoom();
        setupToolbar();
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.add(mandelbrotPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mandelbrotPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {


                 xPress = mouseEvent.getX();

                 yPress = mouseEvent.getY();
                boolean dragged = false;
                model.setDragged(dragged);
                mandelbrotZoom.setX1(xPress);
                mandelbrotZoom.setY1(yPress);


                model.setxPress(xPress);
                model.setyPress(yPress);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                int xPress = mandelbrotZoom.getX1();
                int yPress = mandelbrotZoom.getY1();
                int xRelease = mouseEvent.getX();
                int yRelease = mouseEvent.getY();
                if (xPress > xRelease) {
                    // if box is selected from the right to left
                    mandelbrotZoom.setX1(xRelease);
                    mandelbrotZoom.setX2(xPress);
                } else {
                    mandelbrotZoom.setX1(xPress);
                    mandelbrotZoom.setX2(xRelease);
                }

                if (yPress > yRelease) {
                    // if box is selected from the bottom to the top
                    mandelbrotZoom.setY1(yRelease);
                    mandelbrotZoom.setY2(yPress);
                } else {
                    mandelbrotZoom.setY1(yPress);
                    mandelbrotZoom.setY2(yRelease);
                }

                int x1 = mandelbrotZoom.getX1();
                int x2 = mandelbrotZoom.getX2();
                int y1 = mandelbrotZoom.getY1();
                int y2 = mandelbrotZoom.getY2();
                mandelbrotZoom.setMaxI(model.getMaxI());
                mandelbrotZoom.setMinI(model.getMinI());
                mandelbrotZoom.setMaxR(model.getMaxR());
                mandelbrotZoom.setMinR(model.getMinR());
                mandelbrotZoom.setXR(model.getXR());
                mandelbrotZoom.setYR(model.getYR());

                if (x1 != x2 && y1 != y2) {
                    double minR = mandelbrotZoom.reCalcMinR(x1);
                    double maxR = mandelbrotZoom.reCalcMaxR(x2);
                    double minI = mandelbrotZoom.reCalcMinI(y1);
                    double maxI = mandelbrotZoom.reCalcMaxI(y2);
                    record.pushUndo(model.getData());


                    if (abs(x2 - x1) > abs(y2 - y1)) {
                        int yR = mandelbrotZoom.reCalcYR(x1, x2, y1, y2);
                        System.out.println("yR" + yR);
                        model.setXR(800);
                        model.setYR(yR);

                    } else if (abs(x2 - x1) < abs(y2 - y1)) {
                        int xR = mandelbrotZoom.reCalcXR(x1, x2, y1, y2);
                        System.out.println("xR" + xR);

                        model.setYR(800);

                        model.setXR(xR);
                    }

                    model.setMinR(minR);
                    model.setMaxR(maxR);
                    model.setMaxI(maxI);
                    model.setMinI(minI);
                    model.setDragged(true);
                    model.createModel();
                    mandelbrotPanel.repaint();

                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        mandelbrotPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

                xDrag = mouseEvent.getX();
                yDrag  = mouseEvent.getY();

                model.setyDrag(yDrag);
                model.setxDrag(xDrag);
                mandelbrotPanel.repaint();

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
        });

    }


    public void update(Observable o, Object arg) {

        // Tell the SwingUtilities thread to update the GUI components.
        // This is safer than executing outputField.setText(model.getText())
        // in the caller's thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mandelbrotPanel.repaint();
            }
        });
    }

}
