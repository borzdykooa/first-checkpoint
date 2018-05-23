package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.SaleInfoDaoIF;
import com.borzdykooa.entity.SaleInfo;

public class SaleInfoDao extends BaseDao<Long, SaleInfo> implements SaleInfoDaoIF {

    private static final SaleInfoDao INSTANCE = new SaleInfoDao();

    public static SaleInfoDaoIF getInstance() {
        return INSTANCE;
    }
}
