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
        Challenge challenge = getChallenge(packId, challengeId);
        if(challenge != null){
            for(Puzzle puzzle : challenge.getPuzzles()){
                if (puzzle.id == puzzleId){
                    return puzzle;
                }
            }
        }
        return null;
    }

    public Puzzle getPuzzle(PuzzleReference puzzleReference){
        return getPuzzle(puzzleReference.getPackId(), puzzleReference.getChallengeId(), puzzleReference.getPuzzleId());
    }

    public Challenge getChallenge(int packId, int challengeId){
        Pack pack = getPack(packId);
        if(pack != null){
            for(Challenge challenge : pack.getChallenges()){
                if(challenge.id == challengeId){
                    return challenge;
                }
            }
        }
        return null;
    }

    public Pack getPack(int packId){
        for(Pack pack : packs){
            if(pack.getId() == packId){
                return pack;
            }
        }
        return null;
    }



    public Puzzle getNextPuzzle(PuzzleReference puzzleReference){
        return getPuzzle(puzzleReference.getPackId(), puzzleReference.getChallengeId(), puzzleReference.getPuzzleId() + 1);
    }

    public void setPacks(List<Pack> packs) { this.packs = packs; }

    public String getPuzzleDisplayName(PuzzleReference puzzleReference){
        Challenge challenge = getChallenge(puzzleReference.getPackId(), puzzleReference.getChallengeId());
        if(challenge != null){
            return String.format(challenge.name + " - Level %d", puzzleReference.getPuzzleId());
        }
        return String.format("Level %d", puzzleReference.getPuzzleId());
    }
}
