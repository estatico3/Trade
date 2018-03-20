package com.example.estat.trade.Models;

import java.util.ArrayList;

/**
 * Created by admin on 19.03.18.
 */
public class Order {

    private Payway[] payways;
    private Item[] items;
    private Distway[] distways;
    private String name;
    private State state;

    public Payway[] getPayways() {
        return payways;
    }

    public void setPayways(Payway[] payways) {
        this.payways = payways;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Distway[] getDistways() {
        return distways;
    }

    public void setDistways(Distway[] distways) {
        this.distways = distways;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Order(Payway[] payways, Item[] items, Distway[] distways, String name, State state) {
        this.payways = payways;
        this.items = items;
        this.distways = distways;
        this.name = name;
        this.state = state;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return  name;
    }
}
