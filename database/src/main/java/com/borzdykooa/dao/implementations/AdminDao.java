package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.AdminDaoIF;
import com.borzdykooa.entity.Admin;

import java.util.List;

public class AdminDao extends BaseDao<Long, Admin> implements AdminDaoIF {

    private static final AdminDao INSTANCE = new AdminDao();

    @Override
    public Long save(Admin admin) {
        return super.save(admin);
    }

    @Override
    public Admin find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Admin> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Admin admin) {
        super.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        super.delete(admin);
    }

    public static AdminDaoIF getInstance() {
        return INSTANCE;
    }
}
