package com.example.estat.trade.Models;

/**
 * Created by admin on 19.03.18.
 */
public class Payway {
    private String name;
    private String note;

    public Payway(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public Payway(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
