package com.shopping.mini.repository.impl;

import com.shopping.mini.model.User;
import com.shopping.mini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static com.shopping.mini.util.constants.Constant.USER_TABLE;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;

    @Override
    public User getUserByUserName(String username) {
        String sql = "SELECT * FROM  user WHERE user_name = '"
                + username + "'";
        List<User> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        if (user.isEmpty())
            return new User();
        else
            return user.get(0);
    }

    @Override
    public User addUser(User user) {
        String sqlCheck = "SELECT * FROM  user WHERE user_name = '"
                + user.getUserName() + "'";
        List<User> userList = jdbcTemplate.query(sqlCheck, new BeanPropertyRowMapper<>(User.class));
        if (userList.isEmpty()) {
            String sql = "INSERT INTO " + USER_TABLE + " (`user_name`, `password`, `email`, " +
                    "`first_name`, `last_name`, `date_of_birth`, `gender`, `contact_number`, `address_1`, `address_2`, " +
                    "`city`, `state`, `postal_code`, `country`, `updated_date` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getDateOfBirth());
                ps.setString(7, user.getGender());
                ps.setString(8, user.getContactNumber());
                ps.setString(9, user.getAddress().getAddress1());
                ps.setString(10, user.getAddress().getAddress2());
                ps.setString(11, user.getAddress().getCity());
                ps.setString(12, user.getAddress().getState());
                ps.setString(13, user.getAddress().getPostalCode());
                ps.setString(14, user.getAddress().getCountry());
                ps.setTimestamp(15, user.getUpdatedDate());
                return ps;
            });
            return user;
        } else
            return new User();
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE " + USER_TABLE + " SET" +
                " email = '" + user.getEmail() + "', " +
                " first_name = '" + user.getFirstName() + "', " +
                " last_name = '" + user.getLastName() + "', " +
                " date_of_birth = '" + user.getDateOfBirth() + "', " +
                " gender = '" + user.getGender() + "', " +
                " contact_number = '" + user.getContactNumber() + "', " +
                " address_1 = '" + user.getAddress().getAddress1() + "', " +
                " address_2 = '" + user.getAddress().getAddress2() + "', " +
                " city = '" + user.getAddress().getCity() + "', " +
                " state = '" + user.getAddress().getState() + "', " +
                " postal_code = '" + user.getAddress().getPostalCode() + "', " +
                " country = '" + user.getAddress().getCountry() + "', " +
                " updated_date = '" + user.getUpdatedDate() + "' " +
                " WHERE user_name = '" + user.getUserName() + "'";
        int result = jdbcTemplate.update(sql);
        if (result > 0)
            return user;
        else
            return new User();
    }
}
