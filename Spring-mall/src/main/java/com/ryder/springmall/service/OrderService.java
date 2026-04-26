package com.ryder.springmall.service;

import com.ryder.springmall.dto.CreateOrderRequest;
import com.ryder.springmall.dto.OrderQueryParams;
import com.ryder.springmall.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
