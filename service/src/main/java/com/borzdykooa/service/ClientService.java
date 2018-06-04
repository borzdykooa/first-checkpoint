package com.borzdykooa.service;

import com.borzdykooa.dao.repository.ClientDao;
import com.borzdykooa.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client find(Long id) {
        return clientDao.find(id);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public Long save(Client client) {
        return clientDao.save(client);
    }

    public void delete(Client client) {
        clientDao.delete(client);
    }

    public void update(Client client) {
        clientDao.update(client);
    }
}
