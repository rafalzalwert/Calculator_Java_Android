package com.example.zalwe.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static com.example.zalwe.calculator.MainActivity.history;

public class History extends AppCompatActivity {

    FeedReaderManager feedReaderManager;
    List<DbHelper> equationList;
    ListView list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histrory);
        feedReaderManager = new FeedReaderManager(this);

        equationList = new ArrayList<>();
        list = findViewById(R.id.listView1);

        getEquationsFromDb();
    }
    private void getEquationsFromDb() {



        Cursor cursor = feedReaderManager.getEquation();
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()) {
                equationList.add(new DbHelper(
                        cursor.getInt(0),
                        cursor.getString(1)
                ));
            }
        }


        HistoryAdapter historyAdapter= new HistoryAdapter(this,R.layout.history_list_element,equationList,feedReaderManager);
        list.setAdapter(historyAdapter);

    }

}