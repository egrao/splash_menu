package com.example.splash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = MyOpenHelper.class.getSimpleName();

    // Version has to be 1 first time or app will crash.
    private static final int DATABASE_VERSION = 1;
    private static final String REGISTERED_USERS_TABLE = "users";
    private static final String SCORES_TABLE = "scores";
    private static final String DATABASE_NAME = "registered users";

    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_USER = "user";
    public static final String KEY_PASS = "pass";

    public static final String KEY_SCORE = "_score";
    public static final String KEY_TIME = "time";
    public static final String KEY_GAME = "game";


    // ... and a string array of columns.
    private static final String[] COLUMNS =
            {KEY_USER, KEY_PASS};

    private static final String[] COLUMNS2 =
            {KEY_SCORE, KEY_TIME, KEY_GAME, KEY_USER};

    // Build the SQL query that creates the table.
    private static final String REGISTERED_USERS_TABLE_CREATE =
            "CREATE TABLE " + REGISTERED_USERS_TABLE + " (" +
                    KEY_USER + " TEXT PRIMARY KEY, " + KEY_PASS + " TEXT );";

    private static final String SCORES_TABLE_CREATE =
            "CREATE TABLE " + SCORES_TABLE + " (" +
                    KEY_SCORE + " INTEGER, " + KEY_TIME + " TEXT, " + KEY_GAME + " TEXT, " +
                    KEY_USER + " TEXT, " + "FOREIGN KEY(" + KEY_USER + ") REFERENCES users(user) ON DELETE CASCADE);";


    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct MyOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("ddbb");
        db.execSQL(REGISTERED_USERS_TABLE_CREATE);
        db.execSQL(SCORES_TABLE_CREATE);
//        mWritableDB.execSQL("DELETE FROM users");
//        mWritableDB.execSQL("DELETE FROM scores");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    public void insert(String user, String pass) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_PASS, pass);

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.insert(REGISTERED_USERS_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e);
        }
    }

    public void insertHScore(Integer score, String time, String game, String user) {
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, score);
        values.put(KEY_TIME, time);
        values.put(KEY_GAME, game);
        values.put(KEY_USER, user);

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.insert(SCORES_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e);
        }
    }


    public void delete(String user) {
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.delete(REGISTERED_USERS_TABLE, //table name
                    KEY_USER + " =? ", new String[]{user});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e);        }
    }


    public Boolean search(String searchString, String attribute) {
        Boolean exists = false;
        String[] columns = new String[]{attribute};
        String where =  attribute + " = ?";
        String[] whereArgs = new String[]{searchString};

        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            c = mReadableDB.query(REGISTERED_USERS_TABLE, columns, where, whereArgs, null, null, null);

            if (c != null && c.getCount() > 0) {
                exists = true;
            }

            else{
                return exists;
            }
            c.close();

        } catch (Exception e) {
            Log.d(TAG, "SEARCH EXCEPTION! " + e); // Just log the exception
        }
        return exists;
    }

    public Cursor getUserInfo(String user, String table) {
        String query = "SELECT * FROM "+table+" WHERE user = ?";
        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }

            String[] params = new String[]{user};

            c = mReadableDB.rawQuery(query, params);

        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e); // Just log the exception
        }
        return c;
    }

    public Cursor getScoreC(String user, String game) {
        String query = "SELECT * FROM "+SCORES_TABLE+" WHERE user = ? AND game = ? ORDER BY _score DESC";
        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }

            String[] params = new String[]{user, game};
            c = mReadableDB.rawQuery(query, params);

        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e); // Just log the exception
        }
        return c;
    }

    public Integer getHScore(String user, String game){
        String query = "SELECT MAX(_score) as _score FROM "+SCORES_TABLE+" WHERE user = ? AND game = ? GROUP BY user";
        Integer HScore = 0;
        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }

            String[] params = new String[]{user, game};
            c = mReadableDB.rawQuery(query, params);

            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                HScore = c.getInt(c.getColumnIndexOrThrow(KEY_SCORE));
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e); // Just log the exception
        }
        return HScore;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("bbdd reseteada");
        Log.w(MyOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + REGISTERED_USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCORES_TABLE);
        onCreate(db);
    }
}
