package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.User;

public interface UserDao extends Dao<Long, User> {

    User findByLogin(String login);
}
