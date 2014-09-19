package is.ru.ANDR;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Flow {
    Coordinate a;
    Coordinate b;

    public Flow(Coordinate a, Coordinate b){
        this.a = a;
        this.b = b;
    }

    public Coordinate getCoordinateA(){
        return a;
    }

    public Coordinate getCoordinateB(){
        return b;
    }

    @Override
    public String toString(){
        return "A: " + a.toString() + "\n B: " + b.toString();
    }
}
