package com.ryder.springmall.service.impl;

import com.ryder.springmall.dao.ProductDao;
import com.ryder.springmall.dto.ProductRequest;
import com.ryder.springmall.model.Product;
import com.ryder.springmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
