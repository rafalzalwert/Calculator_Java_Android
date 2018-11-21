package com.example.zalwe.calculator;

public class DbHelper {
    int Id;
    String eqauation;

    public DbHelper(int id, String eqauation) {
        Id = id;
        this.eqauation = eqauation;
    }

    public String getEqauation() {
        return eqauation;
    }
}
