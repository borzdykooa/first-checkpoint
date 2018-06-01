package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.daoInterface.ReviewDao;
import com.borzdykooa.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDaoImpl extends BaseDaoImpl<Long, Review> implements ReviewDao {
}