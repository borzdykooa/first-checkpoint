package com.borzdykooa.dao;

import com.borzdykooa.entity.helpers.IdEntity;

import java.io.Serializable;
import java.util.List;

public interface Dao<PK extends Serializable, T extends IdEntity<PK>> {

    PK save(T object);

    List<T> findAll();

    T find(PK id);

    void update(T object);

    void delete(T object);
}
