package com.example.demoth1.models;

import android.net.Uri;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class CartModel {

    static float sum = 0.0F;
    static ArrayList<Product> productExtint = new ArrayList<>();

    public CartModel(ArrayList<Product> lst) {
        for (Product p: lst) {
            productExtint.add(p);
        }
    }

    public CartModel(Product p) {
        productExtint.add(p);
    }

//  check empty

//  increase number
    public static void increaseNumber(String name, float prc) {
        for (Product p: productExtint) {
            if (p.getName() == name) {
                p.numberPr++;
                p.price += prc;
                return;
            }
        }
    }

    public static ArrayList<Product> getProductExist() {
        return productExtint;
    }

    public String sizeCart() {
        return String.valueOf(productExtint.size());
    }

//  tutorial price
    public static float tutorialPrice() {
        for (Product p: productExtint) {
            sum += p.price;
        }
        return sum;
    }

}
