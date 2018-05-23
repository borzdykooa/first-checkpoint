package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.AdminDaoIF;
import com.borzdykooa.entity.Admin;

public class AdminDao extends BaseDao<Long, Admin> implements AdminDaoIF {

    private static final AdminDao INSTANCE = new AdminDao();

    public static AdminDaoIF getInstance() {
        return INSTANCE;
    }
}
