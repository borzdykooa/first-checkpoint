package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.Client;

public interface ClientDao extends Dao<Long, Client> {

    Client findByLogin(String login);
}
