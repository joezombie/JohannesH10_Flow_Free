package is.ru.ANDR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class CellPath{
    private ArrayList<Coordinate> coordinateArray = new ArrayList<Coordinate>();

    public void addCoordinate(Coordinate coordinate){
        int index = this.coordinateArray.indexOf(coordinate);
        if(index >= 0){
            for (int i = this.coordinateArray.size() - 1; i > index; --i){
                this.coordinateArray.remove(i);
            }
        } else {
            this.coordinateArray.add(coordinate);
        }
    }

    public Coordinate getLastCoordinate(){ return this.coordinateArray.get(this.coordinateArray.size() -1 ); }

    public Coordinate getCoordinate(int i) {return this.coordinateArray.get(i); }

    public int size() { return coordinateArray.size(); };

    public List<Coordinate> getCoordinates() { return this.coordinateArray; }

    public boolean isEmpty() { return this.coordinateArray.isEmpty(); }

    public void reset() { this.coordinateArray.clear(); }
}
