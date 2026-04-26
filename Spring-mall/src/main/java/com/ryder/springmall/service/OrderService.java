package com.ryder.springmall.service;

import com.ryder.springmall.dto.CreateOrderRequest;
import com.ryder.springmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
