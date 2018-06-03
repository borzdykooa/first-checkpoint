package com.borzdykooa.service;

import com.borzdykooa.dao.daoInterface.SaleInfoDao;
import com.borzdykooa.entity.SaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleInfoService {

    private final SaleInfoDao saleInfoDao;

    @Autowired
    public SaleInfoService(SaleInfoDao saleInfoDao) {
        this.saleInfoDao = saleInfoDao;
    }

    public SaleInfo find(Long id) {
        return saleInfoDao.find(id);
    }

    public List<SaleInfo> findAll() {
        return saleInfoDao.findAll();
    }

    public Long save(SaleInfo saleInfo) {
        return saleInfoDao.save(saleInfo);
    }

    public void delete(SaleInfo saleInfo) {
        saleInfoDao.delete(saleInfo);
    }

    public void update(SaleInfo saleInfo) {
        saleInfoDao.update(saleInfo);
    }
}
