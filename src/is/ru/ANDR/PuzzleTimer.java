package is.ru.ANDR;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Johannes Gunnar Heidarsson on 20.9.2014.
 */
public class PuzzleTimer {
    protected Timer timer;
    protected int seconds;
    protected PuzzleTimerTask puzzleTimerTask;
    protected TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            seconds += 1;
            puzzleTimerTask.run();
        }
    };

    public PuzzleTimer(){
        this.timer = new Timer();
        this.seconds = 0;
    }

    public void start(PuzzleTimerTask puzzleTimerTask){
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        this.puzzleTimerTask = puzzleTimerTask;
    }

    public int stop(){
        timer.cancel();
        return seconds;
    }

    public void reset(){
        this.seconds = 0;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString(){
        return intToSecOrMin(seconds/60) + ":" + intToSecOrMin(seconds%60);
    }

    protected String intToSecOrMin(int t){
        return t < 10 ? "0" + Integer.toString(t) : Integer.toString(t);
    }
}
