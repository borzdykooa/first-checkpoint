package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.ClientDao;
import com.borzdykooa.entity.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends BaseDaoImpl<Long, Client> implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client findByLogin(String login) {
            return sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login= :login", Client.class)
                    .setParameter("login", login)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
    }
}
