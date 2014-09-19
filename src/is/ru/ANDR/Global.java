package is.ru.ANDR;

import java.util.HashMap;
import java.util.List;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Global {
    private List<Pack> packs;
    private HashMap<Integer, Pack> test;
    private Global() {}

    private static Global singleInstance = new Global();

    public static Global getSingleInstance() { return singleInstance; }

    public List<Pack> getPacks() { return packs; }

    public Puzzle getPuzzle(int packId, int challengeId, int puzzleId){
        for(Pack pack : packs){
            if(pack.id == packId){
                for(Challenge challenge : pack.getChallenges()){
                    if(challenge.id == challengeId){
                        for(Puzzle puzzle : challenge.getPuzzles()){
                            if (puzzle.id == puzzleId){
                                return puzzle;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }


    public void setPacks(List<Pack> packs) { this.packs = packs; }
}
