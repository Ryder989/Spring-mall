package com.ryder.springmall.dao;

import com.ryder.springmall.dto.ProductRequest;
import com.ryder.springmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
