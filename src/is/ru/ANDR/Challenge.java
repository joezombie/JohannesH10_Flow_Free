package is.ru.ANDR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Challenge {
    int id;
    String name;
    protected List<Puzzle> puzzles;

    public Challenge(int id, String name){
        this.id = id;
        this.name = name;
        puzzles = new ArrayList<Puzzle>();
    }

    public void addPuzzle(Puzzle puzzle){
        this.puzzles.add(puzzle);
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    @Override
    public String toString(){
        String result = "";

        result += "ID: " + Integer.toString(id) + "\n";
        result += "Name: " + name + "\n";
        result += "Puzzles:\n";
        for(Puzzle puzzle : puzzles){
            result += puzzle.toString();
        }
        return result;
    }
}
