package com.borzdykooa.dao.interfaces;

import com.borzdykooa.entity.helpers.IdEntity;

import java.io.Serializable;
import java.util.List;

public interface DaoIF<PK extends Serializable, T extends IdEntity<PK>> {

    PK save(T object);

    List<T> findAll();

    T find(PK id);

    void update(T object);

    void delete(T object);
}
