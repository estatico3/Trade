package com.example.estat.trade.Models;

/**
 * Created by admin on 19.03.18.
 */
public class State {
    private String name;
    private int sort;
    public static final State[] states = {new State("сбор заказов",0),
                                          new State("оплачено",1),
                                          new State("в пункте выдачи",5),
                                          new State("ожидает оплаты",10),};

    public State(String name, int sort) {
        this.name = name;
        this.sort = sort;
    }
    public State(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
