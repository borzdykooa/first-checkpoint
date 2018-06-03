package com.borzdykooa.service;

import com.borzdykooa.dao.daoInterface.OrderingDao;
import com.borzdykooa.entity.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderingService {

    private final OrderingDao orderingDao;

    @Autowired
    public OrderingService(OrderingDao orderingDao) {
        this.orderingDao = orderingDao;
    }

    public Ordering find(Long id) {
        return orderingDao.find(id);
    }

    public List<Ordering> findAll() {
        return orderingDao.findAll();
    }

    public Long save(Ordering ordering) {
        return orderingDao.save(ordering);
    }

    public void delete(Ordering ordering) {
        orderingDao.delete(ordering);
    }

    public void update(Ordering ordering) {
        orderingDao.update(ordering);
    }
}
