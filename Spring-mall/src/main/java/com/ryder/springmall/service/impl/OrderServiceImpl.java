package com.ryder.springmall.service.impl;

import com.ryder.springmall.dao.OrderDao;
import com.ryder.springmall.dao.ProductDao;
import com.ryder.springmall.dto.BuyItem;
import com.ryder.springmall.dto.CreateOrderRequest;
import com.ryder.springmall.model.Order;
import com.ryder.springmall.model.OrderItem;
import com.ryder.springmall.model.Product;
import com.ryder.springmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> OrderItemList = new ArrayList<>();

        for (BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算總金額
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // 轉換BuyItem TO OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            OrderItemList.add(orderItem);

        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,OrderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
