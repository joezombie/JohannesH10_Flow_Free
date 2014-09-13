package is.ru.ANDR;

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

    public int getCol(){ return this.col; }
    public int getRow(){ return this.row; }

    @Override
    public boolean equals(Object other){
        boolean result = false;
        if (other instanceof Coordinate){
            Coordinate otherCo = (Coordinate) other;
            result = otherCo.getCol() == this.getCol() && otherCo.getRow() == this.getRow();
        }
        return result;
    }
}
