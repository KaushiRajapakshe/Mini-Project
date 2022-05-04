package com.shopping.mini.repository;

import com.shopping.mini.model.User;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */

public interface UserRepository {
    User getUserByUserName(String username);

    User addUser(User user);

    User updateUser(User user);
}