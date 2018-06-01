package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.daoInterface.SaleInfoDao;
import com.borzdykooa.entity.SaleInfo;
import org.springframework.stereotype.Repository;

@Repository
public class SaleInfoDaoImpl extends BaseDaoImpl<Long, SaleInfo> implements SaleInfoDao {
}
