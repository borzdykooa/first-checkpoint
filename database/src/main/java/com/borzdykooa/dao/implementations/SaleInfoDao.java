package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.SaleInfoDaoIF;
import com.borzdykooa.entity.SaleInfo;

import java.util.List;

public class SaleInfoDao extends BaseDao<Long, SaleInfo> implements SaleInfoDaoIF {

    private static final SaleInfoDao INSTANCE = new SaleInfoDao();

    @Override
    public Long save(SaleInfo saleInfo) {
        return super.save(saleInfo);
    }

    @Override
    public SaleInfo find(Long id) {
        return super.find(id);
    }

    @Override
    public List<SaleInfo> findAll() {
        return super.findAll();
    }

    @Override
    public void update(SaleInfo saleInfo) {
        super.update(saleInfo);
    }

    @Override
    public void delete(SaleInfo saleInfo) {
        super.delete(saleInfo);
    }

    public static SaleInfoDaoIF getInstance() {
        return INSTANCE;
    }
}
