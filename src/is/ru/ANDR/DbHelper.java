package is.ru.ANDR;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Johannes Gunnar Heidarsson on 20.9.2014.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PUZZLES_DB";
    public static final int DB_VERSION = 1;
    public static final String TablePuzzles = "puzzles";
    public static final String[] TablePuzzlesCols = {"_id", "pack_id", "challenge_id", "puzzle_id", "best_time"};

    private static final String sqlCreateTablePuzzles =
            "CREATE TABLE puzzles(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "pack_id INTEGER NOT NULL," +
            "challenge_id INTEGER NOT NULL," +
            "puzzle_id INTEGER NOT NULL," +
            "best_time INTEGER NOT NULL"+
            ");";

    private static final String sqlDropTablePuzzles =
            "DROP TABLE IF EXISTS puzzles;";

    public DbHelper(Context context){ super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTablePuzzles);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropTablePuzzles);
        onCreate(db);
    }
}
