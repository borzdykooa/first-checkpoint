package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.Dao;
import com.borzdykooa.entity.helpers.IdEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl<PK extends Serializable, T extends IdEntity<PK>> implements Dao<PK, T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.clazz = (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseDaoImpl.class)[1];
    }

    @SuppressWarnings("unchecked")
    public PK save(T entity) {
        return (PK) sessionFactory.getCurrentSession().save(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public T find(PK id) {
        return sessionFactory.getCurrentSession().find(clazz, id);
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery(String.format("select e from %s e", clazz.getSimpleName()), clazz)
                .list();
    }
}
