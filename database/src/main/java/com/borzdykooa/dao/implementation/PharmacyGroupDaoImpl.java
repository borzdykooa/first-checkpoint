package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.daoInterface.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;
import org.springframework.stereotype.Repository;

@Repository
public class PharmacyGroupDaoImpl extends BaseDaoImpl<Long, PharmacyGroup> implements PharmacyGroupDao {
}
