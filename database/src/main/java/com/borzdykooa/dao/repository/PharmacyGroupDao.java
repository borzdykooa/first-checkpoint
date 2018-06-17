package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.PharmacyGroup;

public interface PharmacyGroupDao extends Dao<Long, PharmacyGroup> {

    PharmacyGroup findByName(String name);
}
