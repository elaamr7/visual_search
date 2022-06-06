package com.example.faloka_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faloka_mobile.R;
import com.example.faloka_mobile.api.APIConfig;
import com.example.faloka_mobile.model.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    ArrayList<Product> productArrayList;

    public ProductAdapter() {
        this.productArrayList = new ArrayList<Product>();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product,parent,false);
        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        Glide.with(holder.image.getContext())
                .load(APIConfig.IMAGE_URL+product.getImagePath())
                .into(holder.image);
        holder.name.setText(product.getName());
        Double price = Double.parseDouble(String.valueOf(product.getPrice()));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.price.setText(String.valueOf(formatRupiah.format((double)price)));
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public void updateUserList(final ArrayList<Product> productArrayList) {
        this.productArrayList.clear();
        this.productArrayList = productArrayList;
        notifyDataSetChanged();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;
        public ProductViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
