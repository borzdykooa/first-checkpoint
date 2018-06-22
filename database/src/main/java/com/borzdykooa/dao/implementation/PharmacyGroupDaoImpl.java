package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PharmacyGroupDaoImpl extends BaseDaoImpl<Long, PharmacyGroup> implements PharmacyGroupDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PharmacyGroup> findByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("select p from PharmacyGroup p where p.name like :name", PharmacyGroup.class)
                .setParameter("name", "%" + name.toLowerCase() + "%")
                .list();
    }
}
