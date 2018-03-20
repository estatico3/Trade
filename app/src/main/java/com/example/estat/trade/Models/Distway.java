package com.example.estat.trade.Models;

/**
 * Created by admin on 19.03.18.
 */
public class Distway {
    private String name;
    private String note;

    public Distway(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public Distway(){}
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
