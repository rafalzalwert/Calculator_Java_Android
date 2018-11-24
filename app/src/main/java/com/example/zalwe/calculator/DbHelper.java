package com.example.zalwe.calculator;

class DbHelper {
    private int data_id;
    private String eqauation;

    DbHelper(int id, String eqauation) {
       data_id = id;
        this.eqauation = eqauation;
    }

    String getEqauation() {
        return eqauation;
    }

    int getDataId() {
        return data_id;
    }
}
