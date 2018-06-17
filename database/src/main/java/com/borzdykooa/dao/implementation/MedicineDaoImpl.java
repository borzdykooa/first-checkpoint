package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.MedicineDao;
import com.borzdykooa.dao.repository.PharmacyGroupDao;
import com.borzdykooa.dao.repository.SaleInfoDao;
import com.borzdykooa.dto.PaginationDto;
import com.borzdykooa.dto.SaveMedicineDto;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.SaleInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicineDaoImpl extends BaseDaoImpl<Long, Medicine> implements MedicineDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SaleInfoDao saleInfoDao;

    @Autowired
    private MedicineDao medicineDao;

    @Autowired
    private PharmacyGroupDao pharmacyGroupDao;

    public Long saveMedicine(SaveMedicineDto saveMedicineDto){
        PharmacyGroup pharmacyGroup = pharmacyGroupDao.find(saveMedicineDto.getPharmacyGroup());
        SaleInfo saleInfo=new SaleInfo(saveMedicineDto.getPrice(),saveMedicineDto.getQuantity(),saveMedicineDto.getNeedPrescription());
        saleInfoDao.save(saleInfo);
        Medicine  medicine=new Medicine(saveMedicineDto.getName(),saveMedicineDto.getDescription(),pharmacyGroup,saleInfo);
        return medicineDao.save(medicine);
    }

    @Override
    public List<Medicine> findComplex(PaginationDto paginationDto) {
        return sessionFactory.getCurrentSession().createQuery("select m from Medicine m join m.saleInfo si join m.pharmacyGroup g where "
                + "(lower(m.name) like :partName or :partName is null) and "
                + "(lower(m.description) like :partDescription or :partDescription is null) and "
                + "(si.needPrescription= :needPrescription or :needPrescription is null) ", Medicine.class)
                .setParameter("partName", "%" + paginationDto.getPartName().toLowerCase() + "%")
                .setParameter("partDescription", "%" + paginationDto.getPartDescription().toLowerCase() + "%")
                .setParameter("needPrescription", paginationDto.getNeedPrescription())
                .setMaxResults(paginationDto.getLimit())
                .setFirstResult((paginationDto.getLimit() * paginationDto.getPage()) - paginationDto.getLimit())
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
