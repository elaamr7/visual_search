package com.example.faloka_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.example.faloka_mobile.adapter.ProductAdapter;
import com.example.faloka_mobile.databinding.ActivitySearchListProductBinding;
import com.example.faloka_mobile.model.Product;
import com.example.faloka_mobile.repository.SearchRepository;
import com.example.faloka_mobile.viewmodel.ProductViewModel;

import java.io.File;
import java.util.ArrayList;

public class SearchListProductActivity extends AppCompatActivity {

    ActivitySearchListProductBinding binding;
    ProductViewModel viewModel;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    SearchListProductActivity context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);


        getFileImage();
        setResultProduct();
        setToolbar();
    }
    private File getFileImage(){
        Uri uri = null;
        File file = null;

        if(getIntent() != null){
            uri = Uri.parse(getIntent().getExtras().getString("IMAGE_URI"));
            binding.imgVisSearchResult.setImageURI(uri);
            file = FileUtils.getFile(binding.getRoot().getContext(), uri);
        }
        return file;
    }
    private void setToolbar(){
        setSupportActionBar(binding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }
    private void setResultProduct(){
        recyclerView = findViewById(R.id.rv_product_list);
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Observer<ArrayList<Product>> observer = new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                adapter.updateUserList(products);
            }
        };
        viewModel.getVisualSearchResult(getFileImage()).observe(context,observer);
    }
}