package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.daoInterface.PrescriptionDao;
import com.borzdykooa.entity.Prescription;
import org.springframework.stereotype.Repository;

@Repository
public class PrescriptionDaoImpl extends BaseDaoImpl<Long, Prescription> implements PrescriptionDao {
}
