import java.util.Stack;

/**
 * purpose: record setting for each operation
 */
public class MandelbrotRecord {

    Stack <Object[]> undo = new Stack<>();
    Stack <Object[]> redo = new Stack<>();

    public void pushUndo(Object[] data){
        undo.push(data);
    }

    public Object[] popUndo(){
        Object[] data = undo.pop();
        return data;
    }


    public void pushRedo(Object[] data){
        redo.push(data);
    }

    public Object[] popRedo(){
        Object[] data = redo.pop();
        return data;
    }
}
