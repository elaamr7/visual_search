package com.example.faloka_mobile.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.faloka_mobile.api.APIConfig;
import com.example.faloka_mobile.model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    public static MutableLiveData<ArrayList<Product>> getVisualSearchProduct(File file){
        MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Call<ArrayList<Product>> caller= APIConfig.getApiService().getVisualSearchProducts(photoPart);
        caller.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful()){
                    products.postValue(response.body());
                }
                else{
                    Log.e("gagal response", response.errorBody().toString());
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
