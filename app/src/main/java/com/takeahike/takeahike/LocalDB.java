package com.takeahike.takeahike;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Brady on 5/2/2017.
 */

public class LocalDB {
    private static final String TAG = "***TRAILS**";
    // DB INFO
    private static final String DATABASE_NAME = "trails";
    private static final int DATABASE_VERSION = 1;

    // DB OBJECTS
    private static DatabaseHelper dBHelper = null;
    private static SQLiteDatabase db = null;

    // RETURN CODES FOR USER METHODS
    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;
    public static final int EMAIL_ALREADY_EXISTS = 2;

    static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(Trails_T.CREATE_TABLE);
        }
        @SuppressLint("LongLogTag")
        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data!");
            _db.execSQL(Trails_T.DELETE_TABLE);
            onCreate(_db);
        }
    }

    public static void openDB(Context c)
    {
        if (db == null) {
            dBHelper = new DatabaseHelper(c);
            db = dBHelper.getWritableDatabase();
        }
    }

    public static void closeDB()
    {
        db = null;
        dBHelper.close();
    }

    public static int addTrail(Trail trail)
    {
        assert(db != null);

        ContentValues values = new ContentValues(4);
        values.put(Trails_T.NAME, trail.getName());
        values.put(Trails_T.MILEAGE, trail.getMileage());
        values.put(Trails_T.DESCRIPTION, trail.getDescription());
        values.put(Trails_T.DIFFICULTY, trail.getDifficulty());

        long results = db.insert(Trails_T.TABLE_NAME, null, values);

        if (results == -1)
            return FAILURE;
        else
            return SUCCESS;
    }

    public static ArrayList<Trail> getAllTrails(Context context) {
        assert(db != null);
        String query = Trails_T.GET_ALL_TRAILS;
        String[] data = {};
        Cursor c = db.rawQuery(query, null);

        //Toast.makeText(context, c.toString(),Toast.LENGTH_SHORT).show();

        if (c == null || c.getCount() == 0) {
            return null;
        }

        //Toast.makeText(context, "out loop",Toast.LENGTH_SHORT).show();
        ArrayList<Trail> list = new ArrayList<>();

        c.moveToFirst();
        Log.d(TAG, "cursor size:" + c.getCount());

        int size = c.getCount();
        for (int i = 0; i < size; i++)
        {
            //Toast.makeText(context, "Ran loop",Toast.LENGTH_SHORT).show();
            String name = c.getString(c.getColumnIndex(Trails_T.NAME));
            //Toast.makeText(context, name,Toast.LENGTH_SHORT).show();
            String desc = c.getString(c.getColumnIndex(Trails_T.DESCRIPTION));
            String diff = c.getString(c.getColumnIndex(Trails_T.DIFFICULTY));
            String mileage = c.getString(c.getColumnIndex(Trails_T.MILEAGE));

            list.add(new Trail(name, desc, diff, mileage));
            c.moveToNext();
        }

        return list;
    }

    public static String[] getAllTrailsName(Context context) {
        assert(db != null);
        String query = Trails_T.GET_ALL_TRAILS;
        String[] data = {};
        Cursor c = db.rawQuery(query, null);

        //Toast.makeText(context, c.toString(),Toast.LENGTH_SHORT).show();

        if (c == null || c.getCount() == 0) {
            return null;
        }

        //Toast.makeText(context, "out loop",Toast.LENGTH_SHORT).show();

        c.moveToFirst();
        Log.d(TAG, "cursor size:" + c.getCount());

        int size = c.getCount();

        String[] names = new String[size];

        for (int i = 0; i < size; i++)
        {
            //Toast.makeText(context, "Ran loop",Toast.LENGTH_SHORT).show();
            String name = c.getString(c.getColumnIndex(Trails_T.NAME));

            names[i] = name;
            c.moveToNext();
        }

        return names;
    }
}
