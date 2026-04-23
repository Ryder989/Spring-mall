package com.ryder.springmall.dao.impl;

import com.ryder.springmall.dao.UserDao;
import com.ryder.springmall.dto.UserRegisterRequest;
import com.ryder.springmall.model.User;
import com.ryder.springmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user (email, password, created_date, last_modified_date) " +
                "values (:email, :password, :createdDate, :lastModifieDate)";

        Map<String,Object> map = new HashMap<>();

        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifieDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int user_id = keyHolder.getKey().intValue();

        return user_id;
    }

    @Override
    public User getUserById(Integer user_id) {
        String sql = "Select user_id , email , password , created_date , last_modified_date " +
                "From user Where user_id = :user_id";
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);

        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if (userList.size() > 0 ){
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "Select user_id,email,password,created_date,last_modified_date From user Where email = :email";

        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if (userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }
}
