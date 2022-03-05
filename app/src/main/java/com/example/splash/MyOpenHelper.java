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
                    KEY_SCORE + " INTEGER, " + KEY_TIME + " TEXT, " + KEY_GAME + " TEXT, " + KEY_USER + " TEXT );";


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
//        fillDatabaseWithData(db);
    }

    //    public void fillDatabaseWithData(SQLiteDatabase db) {
//
//        String[] words = {"Android", "Adapter", "ListView", "AsyncTask", "Android Studio",
//                "SQLiteDatabase", "SQLOpenHelper", "Data model", "ViewHolder",
//                "Android Performance", "OnClickListener"};
//
//        // Create a container for the data.
//        ContentValues values = new ContentValues();
//
//        for (int i=0; i < words.length; i++) {
//            // Put column/value pairs for current row into the container.
//            values.put(KEY_WORD, words[i]); // put() overrides existing values.
//            // Insert the row.
//            db.insert(REGISTERED_USERS_TABLE, null, values);
//        }
//    }




    //    public WordItem query(int position) {
//        String query = "SELECT  * FROM " + REGISTERED_USERS_TABLE +
//                " ORDER BY " + KEY_WORD + " ASC " +
//                "LIMIT " + position + ",1";
//
//        Cursor cursor = null;
//        WordItem entry = new WordItem();
//
//        try {
//            if (mReadableDB == null) {
//                mReadableDB = getReadableDatabase();
//            }
//            cursor = mReadableDB.rawQuery(query, null);
//            cursor.moveToFirst();
//            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//            entry.setWord(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
//        } catch (Exception e) {
//            Log.d(TAG, "QUERY EXCEPTION! " + e); // Just log the exception
//        } finally {
//            // Must close cursor and db now that we are done with it.
//            cursor.close();
//            return entry;
//        }
//    }

    //    public long count() {
//        if (mReadableDB == null) {
//            mReadableDB = getReadableDatabase();
//        }
//        return DatabaseUtils.queryNumEntries(mReadableDB, REGISTERED_USERS_TABLE);
//    }


    //      Adds a single word row/entry to the database.
//
//      @param  user New word.
//      @return The id of the inserted word.
//
    public void insert(String user, String pass) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_PASS, pass);
//        values.put(KEY_HSCOREPEG, hscorepeg);
//        values.put(KEY_PEGTIMER, pegtimer);
//        values.put(KEY_HSCORE2048, hscore2048);
//        values.put(KEY_2048TIMER, timer2048);
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

    /**
     * Updates the word with the supplied id to the supplied value.
     *
     * @param id Id of the word to update.
     * @param user The new value of the word.
     * @return the number of rows affected or -1 of nothing was updated.
     */
    public void update(int id, String user, String pass) {
//        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_USER, user);
            values.put(KEY_PASS, pass);
//            values.put(KEY_HSCOREPEG, hscorepeg);
//            values.put(KEY_PEGTIMER, pegtimer);
//            values.put(KEY_HSCORE2048, hscore2048);
//            values.put(KEY_2048TIMER, timer2048);

            mWritableDB.update(REGISTERED_USERS_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

//            mNumberOfRowsUpdated = mWritableDB.update(REGISTERED_USERS_TABLE, //table to change
//                    values, // new values to insert
//                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
//                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e);
        }
//        return mNumberOfRowsUpdated;
    }

    /**
     * Deletes one entry identified by its id.
     *
     * @param id ID of the entry to delete.
     * @return The number of rows deleted. Since we are deleting by id, this should be 0 or 1.
     */
    public void delete(int id) {
//        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.delete(REGISTERED_USERS_TABLE, //table name
                    KEY_ID + " =? ", new String[]{String.valueOf(id)});
//            deleted = mWritableDB.delete(REGISTERED_USERS_TABLE, //table name
//                    KEY_ID + " =? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e);        }
//        return deleted;
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
//            c = mReadableDB.rawQuery("SELECT * FROM users WHERE user = ?", params);
            c = mReadableDB.rawQuery(query, params);
//        String[] strings = new String[2];
//            if (c != null && c.getCount() > 0) {
////                c.moveToFirst();
////                do {
////                    System.out.println("liturgia count "+String.valueOf(c.getCount()));
////                    String user = c.getString(c.getColumnIndexOrThrow("user"));
////                    String pass = c.getString(c.getColumnIndexOrThrow("pass"));
////                    System.out.println("liturgia user"+user);
////                    System.out.println("liturgia user"+pass);
////                    strings[0] = user;
////                    strings[1] = pass;
////                } while (c.moveToNext());
//            }
//            mWritableDB.execSQL("DELETE FROM users");
//            c.close();
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
