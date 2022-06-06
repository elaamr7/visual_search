package com.example.faloka_mobile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.faloka_mobile.model.Product;
import com.example.faloka_mobile.repository.ProductRepository;
import com.example.faloka_mobile.repository.SearchRepository;

import java.io.File;
import java.util.ArrayList;

public class ProductViewModel extends ViewModel {

    MutableLiveData<ArrayList<Product>> productLiveData;

    public MutableLiveData<ArrayList<Product>> getProductLiveData(){
        productLiveData = ProductRepository.getProducts();
        return productLiveData;
    }
    public MutableLiveData<ArrayList<Product>> getVisualSearchResult(File file){
        productLiveData = SearchRepository.getVisualSearchProduct(file);
        return productLiveData;
    }
}
