package com.example.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable {

    public String Membername;
    public Double expense;

    public Member() {

    }

    public Member(String membername, Double expense) {

        this.Membername = membername;
        this.expense = expense;
    }

    public String getMembername() {
        return Membername;
    }

    public void setMembername(String membername) {
        Membername = membername;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
