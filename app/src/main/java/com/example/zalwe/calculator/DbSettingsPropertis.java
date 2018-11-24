package com.example.zalwe.calculator;

import android.provider.BaseColumns;

final class DbSettingsPropertis {

    private DbSettingsPropertis() {}

    static class FeedEntry implements BaseColumns {
        static final String TABLE_NAME = "equation";
        static final String COLUMN_NAME_EQUATION = "equation";
        static final String COLUMN_NAME_DATA_ID = "data_id";


    }
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME_DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEntry.COLUMN_NAME_EQUATION + " TEXT)" ;
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}