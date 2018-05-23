package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.ClientDaoIF;
import com.borzdykooa.entity.Client;

public class ClientDao extends BaseDao<Long, Client> implements ClientDaoIF {

    private static final ClientDao INSTANCE = new ClientDao();

    public static ClientDaoIF getInstance() {
        return INSTANCE;
    }
}
