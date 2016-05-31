package concordia.soen6011.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.*;

public class PlayerDBAdapter {

    //player attributes
    public static final String Player_ID = "_id";
    public static final String Player_NAME = "name";
    public static final String Player_SYMBOL = "symbol";

    //stats attributes
    public static final String Stat_ID = "_id";
    public static final String Stat_PLAYERNAME = "name";
    public static final String Stat_GAMETYPE = "gametype";
    public static final String Stat_STATUS = "gamestatus";

    private DatabaseHelper SQDbHelper;
    private SQLiteDatabase SQDb;

    private static final String DATABASE_NAME = "Leaderboard";
    private static final String TABLE_PLAYER = "Players";
    private static final String TABLE_STATS = "Stats";
    private static final int DATABASE_VERSION = 2;

    private final Context ctx;

    private static final String TABLE_PLAYER_CREATE =
            "CREATE TABLE if not exists " + TABLE_PLAYER + " (" +
                    Player_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Player_NAME + " TEXT," +
                    Player_SYMBOL + " INTEGER );";

    private static final String TABLE_STAT_CREATE =
            "CREATE TABLE if not exists " + TABLE_STATS + " (" +
                    Stat_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Stat_PLAYERNAME + " TEXT," +
                    Stat_GAMETYPE + " TEXT," +
                    Stat_STATUS + " TEXT);";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_PLAYER_CREATE);
            db.execSQL(TABLE_STAT_CREATE);

            //default data
            ContentValues args = new ContentValues();
            args.put(Player_NAME, "Player 1");
            args.put(Player_SYMBOL, 1);

            db.insert(TABLE_PLAYER, null, args);

            args = new ContentValues();
            args.put(Player_NAME, "Player 2");
            args.put(Player_SYMBOL, 1);

            db.insert(TABLE_PLAYER, null, args);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_CREATE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAT_CREATE);
            onCreate(db);
        }
    }

    public PlayerDBAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public PlayerDBAdapter open() throws SQLException {
        SQDbHelper = new DatabaseHelper(ctx);
        SQDb = SQDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (SQDbHelper != null) {
            SQDbHelper.close();
        }
    }

    public long updatePlayer(int id, String name, int symbol) {

        ContentValues args = new ContentValues();
        args.put(Player_NAME, name);
        args.put(Player_SYMBOL, symbol);

        return SQDb.update(TABLE_PLAYER, args, "_id="+id, null);
    }

    public long insertPlayerStat(String playerName, String gameType, String gameStatus) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(Stat_PLAYERNAME, playerName);
        initialValues.put(Stat_GAMETYPE, gameType);
        initialValues.put(Stat_STATUS, gameStatus);

        return SQDb.insert(TABLE_STATS, null, initialValues);
    }

    public List<Player> getPlayers() throws SQLException {

        List<Player> players = new ArrayList<Player>();
        Cursor cursor = null;

        cursor = SQDb.query(true, TABLE_PLAYER, new String[] {Player_ID, Player_NAME, Player_SYMBOL},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setID(cursor.getInt((cursor.getColumnIndex(Player_ID))));
                player.setName((cursor.getString(cursor.getColumnIndex(Player_NAME))));
                player.setSymbol(cursor.getInt(cursor.getColumnIndex(Player_SYMBOL)));

                players.add(player);
            } while (cursor.moveToNext());
        }

        return players;
    }

    public Cursor getPlayersStats() throws SQLException {

        Cursor cursor = null;

        cursor = SQDb.query(true, TABLE_STATS, new String[] {Stat_ID, Stat_PLAYERNAME, Stat_GAMETYPE, Stat_STATUS},
                null, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public long deleteAllStats(){

        return SQDb.delete(TABLE_STATS,null,null);
    }

}
