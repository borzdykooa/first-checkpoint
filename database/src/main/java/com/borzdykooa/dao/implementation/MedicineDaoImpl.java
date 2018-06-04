package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.MedicineDao;
import com.borzdykooa.entity.Medicine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicineDaoImpl extends BaseDaoImpl<Long, Medicine> implements MedicineDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Medicine> findComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription) {
        return sessionFactory.getCurrentSession().createQuery("select m from Medicine m join m.saleInfo si join m.pharmacyGroup g where "
                + "lower(m.name) like :partName and "
                + "lower(m.description) like :partDescription and "
                + "si.needPrescription=:needPrescription", Medicine.class)
                .setParameter("partName", "%" + partName.toLowerCase() + "%")
                .setParameter("partDescription", "%" + partDescription.toLowerCase() + "%")
                .setParameter("needPrescription", needPrescription)
                .setMaxResults(limit)
                .setFirstResult((limit * page) - limit)
                .list();
    }

    @Override
    public List<Medicine> findByPartName(String partName) {
        return sessionFactory.getCurrentSession().createQuery("select m from Medicine m where lower(m.name) like :partName", Medicine.class)
                .setParameter("partName", "%" + partName.toLowerCase() + "%")
                .list();
    }

    @Override
    public List<Medicine> findByGroupId(Long groupId) {
        return sessionFactory.getCurrentSession().createQuery("select m from Medicine m join m.pharmacyGroup g where g.id=:groupId", Medicine.class)
                .setParameter("groupId", groupId)
                .list();
    }
}
