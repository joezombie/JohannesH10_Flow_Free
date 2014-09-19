package is.ru.ANDR;

import android.graphics.Paint;
import org.apache.http.params.CoreConnectionPNames;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class Circle {

    protected int row;
    protected int column;


    public Circle(Coordinate coordinate){
        this.row = coordinate.getRow();
        this.column = coordinate.getCol();
    }

    public int getRow() { return this.row; }
    public int getColumn() { return this.column; }

    public boolean isLocatedAt(int row, int column){
        return this.row == row && this.column == column;
    }

    public boolean isLocatedAt(Coordinate coordinate){
        return ( this.getRow() == coordinate.getRow() && this.getColumn() == coordinate.getCol() );
    }

    @Override
    public boolean equals(Object other){
        boolean results = false;
        if (other instanceof  Circle){
            Circle otherCircle = (Circle) other;
            results = ( otherCircle.getColumn() == this.getColumn() && otherCircle.getRow() == this.getRow() );
        }

        return results;
    }


}
