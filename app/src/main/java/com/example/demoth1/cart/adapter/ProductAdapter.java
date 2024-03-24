package com.example.demoth1.cart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoth1.R;
import com.example.demoth1.models.CartModel;
import com.example.demoth1.models.Product;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<Product> products;    // product
    private ArrayList<Product> carts = new ArrayList<>();     // cart

    private Context context;

    CartModel cartModel;
    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myproduct_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product p = products.get(position);

        holder.imgProduct.setImageURI(p.getImg());
        holder.txtNameProduct.setText(p.getName());
        holder.txtPriceProduct.setText("" + p.getPrice());

//      click  --> add
        holder.imgAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = 0;
                for (Product prt: cartModel.getProductExist()) {
                    if (prt.getName() == p.getName()) {
                        count++;
                    }
                }

//              check empty
                if (count == 0) {
                    cartModel = new CartModel(p);
                } else {
                    cartModel.increaseNumber(p.getName(), p.getPrice());
                }
                Toast.makeText(context, String.valueOf(p.getName()), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct, imgAddProduct;
        TextView txtNameProduct, txtPriceProduct;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            this.imgAddProduct = (ImageView) itemView.findViewById(R.id.imgAddProduct);
            this.txtNameProduct = (TextView) itemView.findViewById(R.id.txtNameProduct);
            this.txtPriceProduct = (TextView) itemView.findViewById(R.id.txtPriceProduct);

        }
    }

}
