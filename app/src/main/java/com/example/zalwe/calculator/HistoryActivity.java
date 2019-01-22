package com.example.zalwe.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    private DbOperations dbOperations;
    private ListView listView;
    Button buttonClearHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histrory_activity);

        buttonClearHistory = findViewById(R.id.buttonDelate);
        listView = findViewById(R.id.listView);

        DbHelper dbHelper = new DbHelper(getBaseContext());
        dbOperations = new DbOperations(dbHelper);
        setListView();
        buttonClearHistory.setOnClickListener(v -> {
            dbOperations.deleteData();
            setListView();
            Intent intent = new Intent(HistoryActivity.this,PopUpActivity.class);
            startActivity(intent);
        });
    }

    private void setListView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.history_list_element,dbOperations.getDataFromDb());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        dbOperations.closeConnection();
        super.onDestroy();
    }


}