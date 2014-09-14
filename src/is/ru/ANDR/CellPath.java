package is.ru.ANDR;

import android.graphics.Color;
import android.util.Log;

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
    protected int color;

    public CellPath(int columnA, int rowA, int columnB, int rowB, int color){
        this.circleArray = new Circle[]{new Circle(columnA, rowA), new Circle(columnB, rowB)};
        this.color = color;
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

    public boolean occupiesCoordinate(Coordinate coordinate){
        boolean result = false;
        result = result || circleArray[0].isLocatedAt(coordinate);
        result = result || circleArray[1].isLocatedAt(coordinate);

        for(Coordinate c: coordinateArray){
            result = result || ( c.getCol() == coordinate.getCol() && c.getRow() == coordinate.getRow());
        }

        return result;
    }

    public boolean isConnected(){ return this.isConnected; }

    public Coordinate getLastCoordinate(){ return this.coordinateArray.get(this.coordinateArray.size() -1 ); }

    public Coordinate getFirstCoordinate(){ return this.coordinateArray.get(0); }

    public Coordinate getCoordinate(int i) {return this.coordinateArray.get(i); }

    public int getColor() {
        return this.color;
    }

    public int size() { return coordinateArray.size(); };

    public List<Coordinate> getCoordinates() { return this.coordinateArray; }

    public boolean isEmpty() { return this.coordinateArray.isEmpty(); }

    public void reset() {
        this.isConnected = false;
        this.coordinateArray.clear();
    }

    @Override
    public boolean equals(Object other){
        boolean result = false;
        if(other instanceof CellPath){
            CellPath otherCellPath = (CellPath) other;
            result = (this.circleArray[0] == otherCellPath.circleArray[0]) && (this.circleArray[1] == otherCellPath.circleArray[1]);
        }

        return result;
    }
}
