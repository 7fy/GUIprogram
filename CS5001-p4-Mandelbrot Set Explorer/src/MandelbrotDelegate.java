
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;



/**
 * The SimpleGuiDelegate class whose purpose is to render relevant state information stored in the model and make changes to the model state based on user events.
 *
 * This class uses Swing to display the model state when the model changes. This is the view aspect of the delegate class.
 * It also listens for user input events (in the listeners defined below), translates these to appropriate calls to methods
 * defined in the model class so as to make changes to the model. This is the controller aspect of the delegate class.
 * The class implements Observer in order to permit it to be added as an observer of the model class.
 * When the model calls notifyObservers() (after executing setChanged())
 * the update(...) method below is called in order to update the view of the model.
 *
 * @author jonl
 *
 */
public class MandelbrotDelegate implements Observer{

    private static final int FRAME_HEIGHT = 800;
    private static final int FRAME_WIDTH = 800;
    private static final int TEXT_WIDTH = 10;

    private JFrame mainFrame;

    private JToolBar toolbar;
    private JTextField inputField;
    private JButton undo;
    private JButton redo;

    private MandelbrotModel model;



    public MandelbrotDelegate(MandelbrotModel model){
        this.model = model;
        this.mainFrame = new JFrame();  // set up the main frame for this GUI
        toolbar = new JToolBar();
        inputField = new JTextField(TEXT_WIDTH);
        setupComponents();
        setupToolbar();

        // add the delegate UI component as an observer of the model
        // so as to detect changes in the model and update the GUI view accordingly
        model.addObserver(this);

    }



    /**
     * Initialises the toolbar to contain the buttons, label, input field, etc. and adds the toolbar to the main frame.
     * Listeners are created for the buttons and text field which translate user events to model object method calls (controller aspect of the delegate)
     */

    private void setupToolbar(){
        undo = new JButton("Undo");
        undo.addActionListener(new ActionListener(){     // to translate event for this button into appropriate model method call
            public void actionPerformed(ActionEvent e){
                // should  call method in model class if you want it to affect model
                JOptionPane.showMessageDialog(mainFrame, "Ooops, Button 1 not linked to model!");
            }
        });
        redo = new JButton("Redo");
        redo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // should  call method in model class if you want it to affect model
                JOptionPane.showMessageDialog(mainFrame, "Ooops, Button 2 not linked to model!");
            }
        });

        JLabel label = new JLabel("Change precision");


        JButton add_button = new JButton("Apply");       // to translate event for this button into appropriate model method call
        add_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            }
        });

        // add buttons, label, and textfield to the toolbar
        toolbar.add(undo);
        toolbar.add(redo);
        toolbar.add(label);
        toolbar.add(inputField);
        toolbar.add(add_button);
        // add toolbar to north of main frame
        mainFrame.add(toolbar, BorderLayout.NORTH);
    }

    private void setupComponents(){
//        setupToolbar();
        mainFrame.setSize (FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(Observable o, Object arg) {

        // Tell the SwingUtilities thread to update the GUI components.
        // This is safer than executing outputField.setText(model.getText())
        // in the caller's thread
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
            }
        });
    }

}
