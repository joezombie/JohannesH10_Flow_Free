package is.ru.ANDR;

import android.graphics.Paint;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class Circle {
    protected int row;
    protected int column;
    protected int color;

    public Circle(int row, int column, int color){
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public int getRow() { return this.row; }
    public int getColumn() { return this.column; }
    public int getColor() { return this.color; }

    public boolean isLocatedAt(int row, int column){
        return this.row == row && this.column == column;
    }

    @Override
    public boolean equals(Object other){
        boolean results = false;
        if (other instanceof  Circle){
            Circle otherCircle = (Circle) other;
            results = (otherCircle.getColumn() == this.getColumn()
                       && otherCircle.getRow() == this.getRow()
                       && otherCircle.getColor() == this.getColor() );
        }

        return results;
    }
}
