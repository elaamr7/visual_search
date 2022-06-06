package com.example.faloka_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.faloka_mobile.adapter.ProductAdapter;
import com.example.faloka_mobile.viewmodel.ProductViewModel;
import com.example.faloka_mobile.databinding.ActivityMainBinding;
import com.example.faloka_mobile.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductViewModel viewModel;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    MainActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();
        setProductList();
    }

    private void setProductList(){
        recyclerView = findViewById(R.id.rv_product_list);
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = ViewModelProviders.of(context).get(ProductViewModel.class);
        Observer<ArrayList<Product>> observer = new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                adapter.updateUserList(products);
            }
        };
        viewModel.getProductLiveData().observe(context,observer);
    }

    private void setToolbar(){

        setSupportActionBar(binding.toolbar.toolbar);
        getSupportActionBar().setTitle("Faloka");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

}