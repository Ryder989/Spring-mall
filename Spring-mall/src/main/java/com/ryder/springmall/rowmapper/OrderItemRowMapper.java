package com.ryder.springmall.rowmapper;

import com.ryder.springmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Nullable
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_item_id(rs.getInt("order_item_id"));
        orderItem.setOrder_id(rs.getInt("order_id"));
        orderItem.setProduct_id(rs.getInt("product_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setAmount(rs.getInt("amount"));

        orderItem.setProductName(rs.getString("product_name"));
        orderItem.setImageUrl(rs.getString("image_url")) ;
        return orderItem;
    }
}
