package com.example.faloka_mobile.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.faloka_mobile.api.APIConfig;
import com.example.faloka_mobile.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    public static MutableLiveData<ArrayList<Product>>  getProducts() {
        MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();
        Call<ArrayList<Product>> caller = APIConfig.getApiService().getProducts();

        caller.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful()){
                    products.postValue(response.body());
                }
                else {
                    Log.e("gagal response", response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.e("gagal load", t.getMessage());
            }
        });
        return products;
    }
}

