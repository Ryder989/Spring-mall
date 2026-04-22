package com.ryder.springmall.rowmapper;

import com.ryder.springmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        // RowMapper欄位不可打錯
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreateDate(rs.getTimestamp("created_date"));
        user.setLastModifieDate(rs.getTimestamp("last_modified_date"));

        return user;
    }
}
