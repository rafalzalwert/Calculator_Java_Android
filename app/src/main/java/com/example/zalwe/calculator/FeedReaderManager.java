package com.example.zalwe.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.zalwe.calculator.FeedReaderContract.SQL_CREATE_ENTRIES;
import static com.example.zalwe.calculator.FeedReaderContract.SQL_DELETE_ENTRIES;

public class FeedReaderManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    boolean putDataIntoSqlliteDb(String equation){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EQUATION, equation);


        return  db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values) != -1;

    }
    Cursor getEquation(){
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME,null);

    }
}