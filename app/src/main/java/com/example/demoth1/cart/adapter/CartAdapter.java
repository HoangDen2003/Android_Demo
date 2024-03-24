package com.example.demoth1.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoth1.R;
import com.example.demoth1.models.CartModel;
import com.example.demoth1.models.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public static ArrayList<Product> alCart;
    private Context mContext;
    CartModel cartModel;

//    public CartAdapter(Context mContext, CartModel cartModel) {
//        this.mContext = mContext;
//        this.cartModel = cartModel;
//    }

    public CartAdapter(Context mContext, ArrayList<Product> alCart) {
        this.mContext = mContext;
        this.alCart = alCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product p = alCart.get(position);

//      khoi tao cac thanh phan
        holder.imgView.setImageURI(p.getImg());
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(p.getPrice() + "");
        holder.editNumberProduce.setText((p.getNumberPr() + 1) + "");
    }

    @Override
    public int getItemCount() {
        return alCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView txtName, txtPlus, txtMinus, txtPrice;
        EditText editNumberProduce;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);

            this.imgView = (ImageView) itemView.findViewById(R.id.imgView);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtPlus = (TextView) itemView.findViewById(R.id.txtPlus);
            this.txtMinus = (TextView) itemView.findViewById(R.id.txtMinus);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            this.editNumberProduce = (EditText) itemView.findViewById(R.id.editNumberProduce);

        }

    }
}
