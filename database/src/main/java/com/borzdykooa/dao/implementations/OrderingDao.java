package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.OrderingDaoIF;
import com.borzdykooa.entity.Ordering;

import java.util.List;

public class OrderingDao extends BaseDao<Long, Ordering> implements OrderingDaoIF {

    private static final OrderingDao INSTANCE = new OrderingDao();

    @Override
    public Long save(Ordering ordering) {
        return super.save(ordering);
    }

    @Override
    public Ordering find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Ordering> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Ordering ordering) {
        super.update(ordering);
    }

    @Override
    public void delete(Ordering ordering) {
        super.delete(ordering);
    }

    public static OrderingDaoIF getInstance() {
        return INSTANCE;
    }
}
