package com.example.faloka_mobile.api;

import com.example.faloka_mobile.model.Product;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @GET("products")
    Call<ArrayList<Product>> getProducts();

    @Multipart
    @POST("products/query")
    Call<ArrayList<Product>> getVisualSearchProducts(
            @Part MultipartBody.Part image);
}
