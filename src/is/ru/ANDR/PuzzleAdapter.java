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

    public long insertPuzzle( PuzzleReference puzzleReference, String lastTime){
        String[] cols = DbHelper.tablePuzzlesCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[1], puzzleReference.getPackId());
        contentValues.put(cols[2], puzzleReference.getChallengeId());
        contentValues.put(cols[3], puzzleReference.getPuzzleId());
        openToWrite();
        long value = db.insert(DbHelper.tablePuzzles, null, contentValues);
        close();
        return value;

    }

    public long updatePuzzle(PuzzleReference puzzleReference, String lastTime){
        String[] cols = DbHelper.tablePuzzlesCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[1], puzzleReference.getPackId());
        contentValues.put(cols[2], puzzleReference.getChallengeId());
        contentValues.put(cols[3], puzzleReference.getPuzzleId());
        openToWrite();
        //cols[1] + sid ?
        long value = db.update(DbHelper.tablePuzzles, contentValues, cols[1], null);
        close();
        return value;
    }

    public Cursor queryPuzzles(){
        openToRead();
        return db.query(DbHelper.tablePuzzles, DbHelper.tablePuzzlesCols, null, null, null, null, null);
    }


    public Cursor queryPuzzle(){
        openToRead();
        String[] cols = DbHelper.tablePuzzlesCols;
        // cols[1] + "" + sid ??
        return db.query(DbHelper.tablePuzzles, cols, cols[1], null, null, null, null);
    }

}
