package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.ClientDao;
import com.borzdykooa.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends BaseDaoImpl<Long, Client> implements ClientDao {
}
