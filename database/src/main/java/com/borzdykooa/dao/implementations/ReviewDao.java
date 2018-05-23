package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.ReviewDaoIF;
import com.borzdykooa.entity.Review;

public class ReviewDao extends BaseDao<Long, Review> implements ReviewDaoIF {

    private static final ReviewDao INSTANCE = new ReviewDao();

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
