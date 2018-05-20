package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.PrescriptionDaoIF;
import com.borzdykooa.entity.Prescription;

import java.util.List;

public class PrescriptionDao extends BaseDao<Long, Prescription> implements PrescriptionDaoIF {

    private static final PrescriptionDao INSTANCE = new PrescriptionDao();

    @Override
    public Long save(Prescription prescription) {
        return super.save(prescription);
    }

    @Override
    public Prescription find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Prescription> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Prescription prescription) {
        super.update(prescription);
    }

    @Override
    public void delete(Prescription prescription) {
        super.delete(prescription);
    }

    public static PrescriptionDao getInstance() {
        return INSTANCE;
    }
}
