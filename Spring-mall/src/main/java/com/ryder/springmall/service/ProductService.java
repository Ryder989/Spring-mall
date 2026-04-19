package com.ryder.springmall.service;

import com.ryder.springmall.constant.ProductCategory;
import com.ryder.springmall.dto.ProductRequest;
import com.ryder.springmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory productCategory,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId ,ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
