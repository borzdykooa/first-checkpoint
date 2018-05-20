package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.ClientDaoIF;
import com.borzdykooa.entity.Client;

import java.util.List;

public class ClientDao extends BaseDao<Long, Client> implements ClientDaoIF {

    private static final ClientDao INSTANCE = new ClientDao();

    @Override
    public Long save(Client client) {
        return super.save(client);
    }

    @Override
    public Client find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Client client) {
        super.update(client);
    }

    @Override
    public void delete(Client client) {
        super.delete(client);
    }

    public static ClientDaoIF getInstance() {
        return INSTANCE;
    }
}
