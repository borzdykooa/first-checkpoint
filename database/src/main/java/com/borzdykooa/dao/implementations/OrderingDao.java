package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.OrderingDaoIF;
import com.borzdykooa.entity.Ordering;

public class OrderingDao extends BaseDao<Long, Ordering> implements OrderingDaoIF {

    private static final OrderingDao INSTANCE = new OrderingDao();

    public static OrderingDaoIF getInstance() {
        return INSTANCE;
    }
}
