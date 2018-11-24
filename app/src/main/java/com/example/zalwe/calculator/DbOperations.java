package com.example.zalwe.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import static com.example.zalwe.calculator.DbSettingsPropertis.FeedEntry.COLUMN_NAME_DATA_ID;
import static com.example.zalwe.calculator.DbSettingsPropertis.FeedEntry.TABLE_NAME;
import static com.example.zalwe.calculator.DbSettingsPropertis.SQL_CREATE_ENTRIES;
import static com.example.zalwe.calculator.DbSettingsPropertis.SQL_DELETE_ENTRIES;

public class DbOperations extends SQLiteOpenHelper {
    DbHelper dbHelper;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    public DbOperations(Context context) {
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
    void putDataIntoSqlliteDb(String equation){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbSettingsPropertis.FeedEntry.COLUMN_NAME_EQUATION, equation);


        db.insert(TABLE_NAME, null, values);

    }
    private Cursor getEquation(){
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

    }
    static void getEquationsFromDb(DbOperations dbOperations, List<DbHelper> equationList) {

        Cursor cursor = dbOperations.getEquation();
        if(cursor.moveToFirst()){
            setListData(equationList, cursor);
        }
    }

    private static void setListData(List<DbHelper> equationList, Cursor cursor) {
        while (cursor.moveToNext()) {
            equationList.add(new DbHelper(
                    cursor.getInt(0),
                    cursor.getString(1)
            ));
        }
    }
    void deleteData() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "1", null);
    }
}