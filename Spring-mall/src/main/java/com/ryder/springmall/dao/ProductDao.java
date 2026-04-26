package com.ryder.springmall.dao;

import com.ryder.springmall.constant.ProductCategory;
import com.ryder.springmall.dto.ProductQueryParams;
import com.ryder.springmall.dto.ProductRequest;
import com.ryder.springmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId ,ProductRequest productRequest);

    void deleteProductById(Integer productId);

    void updateStock(Integer productId,Integer stock);
}
