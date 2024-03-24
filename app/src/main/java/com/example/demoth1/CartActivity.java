package com.example.demoth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoth1.cart.adapter.CartAdapter;
import com.example.demoth1.models.CartModel;
import com.example.demoth1.models.Product;
import com.example.demoth1.models.ProductRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Button btnTmp;
    private RecyclerView rvCart;

    private TextView txtTotal;
//    CartAdapter cartAdapter;
    CartModel cartModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rvCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnTmp = findViewById(R.id.btn_tmp);

        setUp();

//        btnTmp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(CartActivity.this, String.valueOf(cartModel.getProductExtint().size()), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void setUp() {
//      set gridlayout cho recycleview
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvCart.setLayoutManager(mLayoutManager);

        CartAdapter cartAdapter = new CartAdapter(this, cartModel.getProductExist());
        rvCart.setAdapter(cartAdapter);
//        Toast.makeText(CartActivity.this, String.valueOf(cartAdapter.getItemCount()), Toast.LENGTH_SHORT).show();

        txtTotal.setText(cartModel.tutorialPrice() + "");

    }

}