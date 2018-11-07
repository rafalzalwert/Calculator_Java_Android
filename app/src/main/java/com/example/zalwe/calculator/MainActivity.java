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


    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
    private int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,R.id.btnHistory};
    private TextView txtScreen;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;
    public static ArrayList<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtScreen = (TextView) findViewById(R.id.txtScreen);
        setNumericOnClickListener();
        setOperatorOnClickListener();

        history = new ArrayList<String>();
    }


    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    txtScreen.setText(button.getText());
                    stateError = false;
                } else {
                    txtScreen.append(button.getText());
                }
                lastNumeric = true;
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    txtScreen.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    txtScreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtScreen.setText("");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
        findViewById(R.id.btnHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void onEqual() {
        if (lastNumeric && !stateError) {
            String txt = txtScreen.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {

                double result = expression.evaluate();
                String equationWithresult=txt+"="+Double.toString(result);
                txtScreen.setText(Double.toString(result));
                history.add(equationWithresult);
                lastDot = true;
            } catch (ArithmeticException ex) {
                txtScreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }



}