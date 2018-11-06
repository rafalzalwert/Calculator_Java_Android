package com.example.zalwe.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class History extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histrory);

        ListView list = findViewById(R.id.listView1);

        Intent intent = getIntent();


        ArrayList<String> history = new ArrayList<String>(Collections.<String>singletonList(String.valueOf(MainActivity.history)));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.history_list_element, history);

        list.setAdapter(adapter);
    }
}
