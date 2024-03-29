package com.iit.myactivitytracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iit.myactivitytracker.database.tables.RecordsTable;
import com.iit.myactivitytracker.database.tables.RemindersTable;

/**
 * Created by slouma on 22/02/2015.
 */
public class TrackerDatabaseHelper extends SQLiteOpenHelper {

    // database name
    private static final String DATABASE_NAME = "my-tracker.db";
    // data base version
    private static final int DATABASE_VERSION = 1;

    /**
     * Basic constructor
     *
     * @param context
     */
    public TrackerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public TrackerDatabaseHelper(Context context, String name,
                                 SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        RecordsTable.onCreate(db);
        RemindersTable.onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        RecordsTable.onUpgrade(db, oldVersion, newVersion);
        RemindersTable.onUpgrade(db, oldVersion, newVersion);
    }

}