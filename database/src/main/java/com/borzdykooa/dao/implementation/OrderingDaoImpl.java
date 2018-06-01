package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.daoInterface.OrderingDao;
import com.borzdykooa.entity.Ordering;
import org.springframework.stereotype.Repository;

@Repository
public class OrderingDaoImpl extends BaseDaoImpl<Long, Ordering> implements OrderingDao {
}
