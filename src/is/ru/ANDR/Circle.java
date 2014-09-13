package is.ru.ANDR;

import android.graphics.Paint;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class Circle {
    protected int row;
    protected int column;
    protected int color;

    public Circle(int column, int row){
        this.row = row;
        this.column = column;
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
