package com.borzdykooa.service;

import com.borzdykooa.dao.repository.UserDao;
import com.borzdykooa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
