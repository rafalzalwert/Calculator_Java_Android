package com.example.zalwe.calculator;

import android.provider.BaseColumns;

final class DbSettingsPropertis {

    private DbSettingsPropertis() {}


        static class DbEntry implements BaseColumns {

            private DbEntry() {
            }

            static final String TABLE_NAME = "equation";
            static final String COLUMN_NAME_EQUATION = "equation";


        }
    }