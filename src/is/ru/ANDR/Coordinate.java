package is.ru.ANDR;

import android.util.Log;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class Coordinate {
    private int col;
    private int row;

    public Coordinate(int col, int row){
        this.col = col;
        this.row = row;
    }

    public Coordinate(){
        this.col = 0;
        this.row = 0;
    }


    public int getCol(){ return this.col; }

    public void setCol(int col) { this.col = col; }

    public int getRow(){ return this.row; }

    public void setRow(int row) { this.row = row; }

    public boolean isLocatedAt(Coordinate other){
        return (this.col == other.col && this.row == other.row);
    }

    @Override
    public boolean equals(Object other){
        boolean result = false;
        if (other instanceof Coordinate){
            Coordinate otherCo = (Coordinate) other;
            result = ( this.row == otherCo.row && this.col == otherCo.col );
        }
        return result;
    }

    @Override
    public String toString(){
        return "Col:" + Integer.toString(this.col) + " Row:" + Integer.toString(this.row) + "\n";
    }
}
