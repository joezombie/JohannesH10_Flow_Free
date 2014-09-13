package is.ru.ANDR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class CellPath{
    protected ArrayList<Coordinate> coordinateArray = new ArrayList<Coordinate>();
    protected Circle[] circleArray;
    protected boolean isConnected = false;

    public CellPath(int columnA, int rowA, int columnB, int rowB){
        this.circleArray = new Circle[]{new Circle(columnA, rowA), new Circle(columnB, rowB)};
    }

    public List<Circle> getCircleList() {
        return Arrays.asList(this.circleArray);
    }

    public void addCoordinate(Coordinate coordinate){
        int index = this.coordinateArray.indexOf(coordinate);
        if(index >= 0){
            for (int i = this.coordinateArray.size() - 1; i > index; --i){
                this.coordinateArray.remove(i);
            }
        } else {
            this.coordinateArray.add(coordinate);
            if( (circleArray[0].isLocatedAt(coordinate) && circleArray[1].isLocatedAt(getFirstCoordinate()))
               || ( circleArray[0].isLocatedAt(getFirstCoordinate()) && circleArray[1].isLocatedAt(coordinate)) ){
                this.isConnected = true;
            }
        }
    }

    public boolean isConnected(){ return this.isConnected; }

    public Coordinate getLastCoordinate(){ return this.coordinateArray.get(this.coordinateArray.size() -1 ); }

    public Coordinate getFirstCoordinate(){ return this.coordinateArray.get(0); }

    public Coordinate getCoordinate(int i) {return this.coordinateArray.get(i); }

    public int size() { return coordinateArray.size(); };

    public List<Coordinate> getCoordinates() { return this.coordinateArray; }

    public boolean isEmpty() { return this.coordinateArray.isEmpty(); }

    public void reset() {
        this.isConnected = false;
        this.coordinateArray.clear();
    }
}
