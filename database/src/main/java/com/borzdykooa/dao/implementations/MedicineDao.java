package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.MedicineDaoIF;
import com.borzdykooa.entity.Medicine;
import org.hibernate.Session;

import java.util.List;

public class MedicineDao extends BaseDao<Long, Medicine> implements MedicineDaoIF {

    private static final MedicineDao INSTANCE = new MedicineDao();

    @Override
    public List<Medicine> findComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription, Long groupId) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session.createQuery("select m from Medicine m join m.saleInfo si join m.pharmacyGroup g where " +
                    "lower(m.name) like :partName and " +
                    "lower(m.description) like :partDescription and " +
                    "g.id=:groupId and " +
                    "si.needPrescription=:needPrescription", Medicine.class)
                    .setParameter("partName", "%" + partName.toLowerCase() + "%")
                    .setParameter("partDescription", "%" + partDescription.toLowerCase() + "%")
                    .setParameter("needPrescription", needPrescription)
                    .setParameter("groupId", groupId)
                    .setMaxResults(limit)
                    .setFirstResult((limit * page) - limit)
                    .list();
        }
    }

    @Override
    public List<Medicine> findByPartName(String partName) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session
                    .createQuery("select m from Medicine m where lower(m.name) like :partName", Medicine.class)
                    .setParameter("partName", "%" + partName.toLowerCase() + "%")
                    .list();
        }
    }

    @Override
    public List<Medicine> findByGroupId(Long groupId) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session
                    .createQuery("select m from Medicine m join m.pharmacyGroup g where g.id=:groupId", Medicine.class)
                    .setParameter("groupId", groupId)
                    .list();
        }
    }

    @Override
    public Long save(Medicine medicine) {
        return super.save(medicine);
    }

    @Override
    public Medicine find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Medicine> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Medicine medicine) {
        super.update(medicine);
    }

    @Override
    public void delete(Medicine medicine) {
        super.delete(medicine);
    }

    public static MedicineDaoIF getInstance() {
        return INSTANCE;
    }
}
