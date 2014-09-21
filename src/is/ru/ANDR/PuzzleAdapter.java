package is.ru.ANDR;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Johannes Gunnar Heidarsson on 20.9.2014.
 */
public class PuzzleAdapter {
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private Context context;

    public PuzzleAdapter( Context context){
        this.context = context;
    }

    public PuzzleAdapter openToRead(){
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public PuzzleAdapter openToWrite(){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){ db.close(); }

    public long insertPuzzle( PuzzleReference puzzleReference, int bestTime){
        Cursor cursor = queryPuzzle(puzzleReference);
        if(cursor.getCount() == 0) {
            String[] cols = DbHelper.TablePuzzlesCols;
            ContentValues contentValues = new ContentValues();
            contentValues.put(cols[1], puzzleReference.getPackId());
            contentValues.put(cols[2], puzzleReference.getChallengeId());
            contentValues.put(cols[3], puzzleReference.getPuzzleId());
            contentValues.put(cols[4], bestTime);
            openToWrite();
            long value = db.insert(DbHelper.TablePuzzles, null, contentValues);
            close();
            return value;
        } else {
            return -1;
        }

    }

    public long updatePuzzle(PuzzleReference puzzleReference, int bestTime){
        String[] cols = DbHelper.TablePuzzlesCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[1], puzzleReference.getPackId());
        contentValues.put(cols[2], puzzleReference.getChallengeId());
        contentValues.put(cols[3], puzzleReference.getPuzzleId());
        contentValues.put(cols[4], bestTime);
        openToWrite();

        long value = db.update(DbHelper.TablePuzzles, contentValues, puzzleReference.getWhereString(), null);
        close();
        return value;
    }

    public Cursor queryPuzzles(){
        openToRead();
        return db.query(DbHelper.TablePuzzles, DbHelper.TablePuzzlesCols, null, null, null, null, null);
    }


    public Cursor queryPuzzle(PuzzleReference puzzleReference){
        openToRead();
        return  db.query(DbHelper.TablePuzzles, DbHelper.TablePuzzlesCols, puzzleReference.getWhereString(), null, null, null, null);
    }

    public Cursor queryPuzzleInPack(int packId){
        openToRead();
        String where = String.format("pack_id=%d", packId);
        return  db.query(DbHelper.TablePuzzles, DbHelper.TablePuzzlesCols, where, null, null, null, null);
    }

    public Cursor queryPuzzleInChallenge(int packId, int challengeId){
        openToRead();
        String where = String.format("pack_id=%d AND challenge_id=%d", packId, challengeId);
        return db.query(DbHelper.TablePuzzles, DbHelper.TablePuzzlesCols, where, null, null, null, null);
    }
}
