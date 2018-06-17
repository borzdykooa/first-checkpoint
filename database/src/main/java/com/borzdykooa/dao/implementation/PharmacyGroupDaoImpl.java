package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PharmacyGroupDaoImpl extends BaseDaoImpl<Long, PharmacyGroup> implements PharmacyGroupDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PharmacyGroup findByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("select p from PharmacyGroup p where p.name=:name", PharmacyGroup.class)
                .setParameter("name", name)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
