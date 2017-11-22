import java.util.Observable;

public class MandelbrotModel extends Observable{
    private int[][] model;

    public MandelbrotModel(){
        this.model = model;

    }

    public int[][] getModel() {
        return model;
    }


    public void setModel(int[][]model){
        if(this.model!=model){
            this.model=model;
            setChanged();
            this.notifyObservers(model);
        }

    }
}
