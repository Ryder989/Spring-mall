package com.ryder.springmall.dao.impl;

import com.ryder.springmall.dao.OrderDao;
import com.ryder.springmall.dto.OrderQueryParams;
import com.ryder.springmall.model.Order;
import com.ryder.springmall.model.OrderItem;
import com.ryder.springmall.rowmapper.OrderItemRowMapper;
import com.ryder.springmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order`(user_id,total_amount,created_date,last_modified_date) " +
                "VALUES (:userId,:totalAmount,:createDate,:lastModifieDate)";

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("totalAmount",totalAmount);

        Date now = new Date();
        map.put("createDate",now);
        map.put("lastModifieDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        // 使用 batchUpdate 一次性加入數據
        String sql = "INSERT INTO order_item(order_id,product_id,quantity,amount) " +
                "VALUES (:orderId,:productId,:quantity,:amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0 ; i < orderItemList.size(); i++){
            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderItem.getProduct_id());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());


        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id,user_id,total_Amount,created_date,last_modified_date " +
                "FROM `order` WHERE order_id = :orderId";

        Map<String,Object> map =new HashMap<>();
        map.put("orderId",orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        if (orderList.size()>0){
            return orderList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT T0.order_item_id,T0.order_id,T0.product_id,T0.quantity,T0.amount,T1.product_name,T1.image_url " +
                "FROM order_item as T0 " +
                "LEFT JOIN product as T1 ON T0.product_id = T1.product_id " +
                "WHERE T0.order_id = :orderId ";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql,map,new OrderItemRowMapper());

        return orderItemList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql ="SELECT count(*) FROM `order` WHERE 1=1";

        Map<String,Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date FROM `order` WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        // 排序
        sql = sql + " ORDER BY created_date DESC";

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",orderQueryParams.getLimit());
        map.put("offset",orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        return orderList;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null){
            sql = sql + " AND user_id = :userId";
            map.put("userId",orderQueryParams.getUserId());
        }
        return sql;
    }
}
