package com.example.demoth1.models;

import android.net.Uri;

public class Product {
    int id;
    String name;
    float price;
    Uri img;
    int numberPr;

    public Product() {
    }
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Product(int id, String name, float price, Uri img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.numberPr = 1;
    }

    public int getNumberPr() {
        return numberPr;
    }

    public void setNumberPr(int numberPr) {
        this.numberPr = numberPr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}
