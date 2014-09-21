package is.ru.ANDR;

/**
 * Created by Johannes Gunnar Heidarsson on 19.9.2014.
 */
public class PuzzleReference {
    protected int packId;
    protected int challengeId;
    protected int puzzleId;

    public PuzzleReference(int packId, int challengeId, int puzzleId){
        this.packId = packId;
        this.challengeId = challengeId;
        this.puzzleId = puzzleId;
    }

    public int getPackId() {
        return packId;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public String getWhereString(){
        return String.format("pack_id=%d AND challenge_id=%d AND puzzle_id=%d",
                              getPackId(),getChallengeId(),getPuzzleId());
    }
}
