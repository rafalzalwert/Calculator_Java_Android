package com.example.zalwe.calculator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;




class DbOperations {

    private DbHelper dbHelper;

    DbOperations(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    void putDataIntoSqlliteDb(String equation){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbSettingsPropertis.DbEntry.COLUMN_NAME_EQUATION, equation);


        db.insert(DbSettingsPropertis.DbEntry.TABLE_NAME, null, values);

    }
    List<String> getDataFromDb(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbSettingsPropertis.DbEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        final List<String> equations = new ArrayList<>();

        while(cursor.moveToNext()){
            String equation = cursor.getString(
                    cursor.getColumnIndexOrThrow(DbSettingsPropertis.DbEntry.COLUMN_NAME_EQUATION));

            equations.add(equation);

        }
        cursor.close();

        return equations;
    }
    void deleteData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DbSettingsPropertis.DbEntry.TABLE_NAME, "1", null);
    }

    void closeConnection(){
        dbHelper.close();
    }
}