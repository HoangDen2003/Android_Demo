package com.example.demoth1.models;

import java.util.ArrayList;

public class ProductRepository {
    private static ArrayList<Product> productList = new ArrayList<>();

    public ProductRepository() {
//        ...
    }

    public ProductRepository(ArrayList<Product> lst) {
        for (Product p: lst) {
            productList.add(p);
        }
    }

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public void addProductList(Product p) {

        productList.add(p);

    }

    public static void setProductList(ArrayList<Product> productList) {
        ProductRepository.productList = productList;
    }

    public Product getProduct(Integer id) {
        Product result;
        for (Product p: productList) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
}
