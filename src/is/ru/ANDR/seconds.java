package is.ru.ANDR;

/**
 * Created by Johannes Gunnar Heidarsson on 23.9.2014.
 */
public class Seconds {
    int seconds;

    public Seconds(){
        this.seconds = 0;
    }

    public Seconds(int seconds){
        this.seconds = seconds;
    }

    @Override
    public String toString(){
        return String.format("%02d:%02d", seconds/60, seconds%60);
    }
}
