package com.borzdykooa.service;

import com.borzdykooa.dao.daoInterface.AdminDao;
import com.borzdykooa.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Admin find(Long id) {
        return adminDao.find(id);
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Long save(Admin admin) {
        return adminDao.save(admin);
    }

    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

    public void update(Admin admin) {
        adminDao.update(admin);
    }
}
