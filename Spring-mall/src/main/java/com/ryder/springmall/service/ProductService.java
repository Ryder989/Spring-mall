package com.ryder.springmall.service;

import com.ryder.springmall.dto.ProductRequest;
import com.ryder.springmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
