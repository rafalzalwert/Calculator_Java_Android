package com.example.zalwe.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    DbOperations dbOperations;
    List<DbHelper> equationList;
    ListView list;
    Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histrory);
        dbOperations = new DbOperations(this);
        button = findViewById(R.id.buttonDelate);
        equationList = new ArrayList<>();
        list = findViewById(R.id.listView1);
        DbOperations.getEquationsFromDb(dbOperations,equationList);

        setArrayAdapter();

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dbOperations.deleteData();
               refreshIntent();
               Intent intent = new Intent(History.this,PopUpActivity.class);
               startActivity(intent);
           }
       });
    }

    private void setArrayAdapter(){
        HistoryAdapter historyAdapter= new HistoryAdapter(this,R.layout.history_list_element,equationList, dbOperations);
        list.setAdapter(historyAdapter);
    }
    private void refreshIntent(){
            finish();
            startActivity(getIntent());
    }




}