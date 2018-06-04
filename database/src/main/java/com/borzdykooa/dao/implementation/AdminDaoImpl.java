package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.AdminDao;
import com.borzdykooa.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Long, Admin> implements AdminDao {
}
