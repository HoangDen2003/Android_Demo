package com.example.demoth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demoth1.cart.adapter.ProductAdapter;
import com.example.demoth1.models.CartModel;
import com.example.demoth1.models.Product;
import com.example.demoth1.models.ProductRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    ProductRepository productRepository;
    RecyclerView rvProduct;
    ImageView menuCart;
//    private CartModel cartModel = new CartModel();

    ArrayList<Product> alProduct;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
//      khoi tao product
        initData();
        rvProduct = findViewById(R.id.recycleViewProduct);
        menuCart = findViewById(R.id.menuCart);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(mLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter(this, this.productRepository.getProductList());
        rvProduct.setAdapter(productAdapter);

        Toast.makeText(this, String.valueOf(productAdapter.getItemCount()), Toast.LENGTH_SHORT).show();

        menuCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

    }

    public void initData() {
        alProduct = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Product p = new Product();
//          image
            int resId = getResId("macbook_" + (i + 1), R.drawable.class);
//          return resource image
            Uri imgUri = getUri(resId);
            p.setName("Macbook " + (i + 1));
            p.setImg(imgUri);
            p.setPrice(120.00F * (i + 1));
            alProduct.add(p);
        }
        this.productRepository = new ProductRepository(alProduct);

    }

    private Uri getUri(int resId) {
        return Uri.parse("android.resource://" + this.getPackageName().toString() + "/" + resId);
    }

    private static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

//  create menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////      handle item selected
////        switch (item.getItemId()) {
////            case R.id.cartShopping:
////                Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show();
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
//
//        int id = item.getItemId();
//        if (id == R.id.cartShopping) {
//            Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}