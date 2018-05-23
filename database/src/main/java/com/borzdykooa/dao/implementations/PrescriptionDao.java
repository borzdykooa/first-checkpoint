package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.PrescriptionDaoIF;
import com.borzdykooa.entity.Prescription;

public class PrescriptionDao extends BaseDao<Long, Prescription> implements PrescriptionDaoIF {

    private static final PrescriptionDao INSTANCE = new PrescriptionDao();

    public static PrescriptionDao getInstance() {
        return INSTANCE;
    }
}
