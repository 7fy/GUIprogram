package model;

import java.util.Observable;

/**
 * A simple model class whose purpose is to store text entered by a user.
 *
 * The model's state is altered by user input when the delegate calls the addText()
 * method below.
 *
 * The model extends the Observable class and is observed by the Delegate class. This form
 * of loose coupling permits the Delegate (View) to be updated when the model has changed.
 *
 * @author jonl
 *
 */
public class SimpleModel extends Observable {

    /**
     * the text to store
     */
    private StringBuffer text;

    /**
     * Constructs a new SimpleModel instance.
     * Initialises the StringBuffer.
     */
    public SimpleModel(){
        text = new StringBuffer();
    }

    /**
     * This method is called by the GUI control aspect of the delegate to update the model according to user input
     * The method adds the text entered by the user to its buffer and calls setChanged() and notifiyObservers()
     * so that the GUI view is notified that the model has changed and can update the display
     * @param str the string to add to the buffer
     */
    public void addText(String str){
        text.append("\n" + str);
        setChanged();
        notifyObservers();
    }


    /**
     * This method is needed by the GUI to get hold of model state when updating the output text area in the GUI.
     * @return the content of the model's text buffer
     */
    public String getText(){
        return text.toString();
    }


}