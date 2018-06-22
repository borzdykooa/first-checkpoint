package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.PharmacyGroup;

import java.util.List;

public interface PharmacyGroupDao extends Dao<Long, PharmacyGroup> {

    List<PharmacyGroup> findByName(String name);
}
