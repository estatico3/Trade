package com.example.estat.trade.Models;

/**
 * Created by admin on 19.03.18.
 */
public class Item {

    private double price;
    private String name;
    private int qty;
    private String photo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Item() {
    }

    public Item(double price, String name, int qty, String photo) {
        this.price = price;
        this.name = name;
        this.qty = qty;
        this.photo = photo;
    }
}
