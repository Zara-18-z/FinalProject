package com.example.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MemberExpense implements Serializable {

    public HashMap<String, Double> hmap = new HashMap<String, Double>();

    public MemberExpense() {

    }

    public MemberExpense(HashMap<String, Double> hmap) {
        this.hmap = hmap;
    }

    public HashMap<String, Double> getHmap() {
        return hmap;
    }

    public void setHmap(HashMap<String, Double> hmap) {
        this.hmap = hmap;
    }
}



