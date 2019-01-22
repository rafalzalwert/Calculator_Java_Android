package com.example.zalwe.calculator;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;

class ExpresionValidation {

    private static final String[] operations = {"+", "-", "*", "/", "."};

    private ExpresionValidation(){}

    static boolean isOperationCanBeEvaluate(@NonNull String expression) {
        if(!expression.equals(""))
        {
            String match = String.valueOf(expression.charAt(expression.length() - 1));
            return Arrays.stream(operations).noneMatch(match::equals);
        }
        return false;
    }

    static double calc(String string,Expression expression){
        return expression.evaluate();
    }
    @SuppressLint("SetTextI18n")
    private static void tryGetEquation(String string, Expression expression, TextView textView){

        try {
           textView.setText(string+"="+String.valueOf(calc(string,expression)));
        }catch (ArithmeticException ex){
            textView.setText("Error");
        }
    }

    static void onEqual(String string,TextView textView){
        Expression expression = new ExpressionBuilder(string).build();
        tryGetEquation(string,expression,textView);
    }
}
