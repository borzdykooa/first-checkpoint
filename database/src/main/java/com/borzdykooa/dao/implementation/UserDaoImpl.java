package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.UserDao;
import com.borzdykooa.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByLogin(String login) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u where u.login = :login", User.class)
                .setParameter("login", login)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
