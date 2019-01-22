package com.example.zalwe.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};


    private TextView txtScreen;

    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtScreen = findViewById(R.id.txtScreen);

        DbHelper dbHelper = new DbHelper(getBaseContext());
        dbOperations = new DbOperations(dbHelper);

       setNumericOnClickListener();

        final Button buttonDiv = findViewById(R.id.btnDivide);
        final Button buttonMulti = findViewById(R.id.btnMultiply);
        final Button buttonAdd = findViewById(R.id.btnAdd);
        final Button buttonSub = findViewById(R.id.btnSubtract);
        Button buttonHistory = findViewById(R.id.btnHistory);
        Button buttonC = findViewById(R.id.btnClear);
        final Button buttonEval = findViewById(R.id.btnEqual);
        final Button buttonDot = findViewById(R.id.btnDot);
        buttonC.setOnClickListener(v -> txtScreen.setText(""));

        buttonHistory.setOnClickListener(v -> showHistory());

        buttonDot.setOnClickListener(v -> {
            if(ExpresionValidation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonDot.getText());
            }
        });
        buttonDiv.setOnClickListener(v -> {
            if(ExpresionValidation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonDiv.getText());
            }
        });
        buttonMulti.setOnClickListener(v -> {
            if(ExpresionValidation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonMulti.getText());
            }
        });

        buttonAdd.setOnClickListener(v -> {
            if(ExpresionValidation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonAdd.getText());
            }
        });
        buttonSub.setOnClickListener(v -> {
            if(ExpresionValidation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonSub.getText());
            }
        });
        buttonEval.setOnClickListener(v -> {
            String expression =  txtScreen.getText().toString();
            ExpresionValidation.onEqual(expression,txtScreen);
            String expressionWithResult = txtScreen.getText().toString();
            if(!expressionWithResult.equals("Error")){
                dbOperations.putDataIntoSqlliteDb(expressionWithResult);
            }
        });

    }
    private void setNumericOnClickListener() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            txtScreen.append(button.getText());
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        dbOperations.closeConnection();
        super.onDestroy();
    }



    private void showHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        txtScreen.setText("");
        startActivity(intent);
    }
}