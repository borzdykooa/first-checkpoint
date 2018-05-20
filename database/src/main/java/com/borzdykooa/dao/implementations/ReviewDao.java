package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.ReviewDaoIF;
import com.borzdykooa.entity.Review;

import java.util.List;

public class ReviewDao extends BaseDao<Long, Review> implements ReviewDaoIF {

    private static final ReviewDao INSTANCE = new ReviewDao();

    @Override
    public Long save(Review review) {
        return super.save(review);
    }

    @Override
    public Review find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Review> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Review review) {
        super.update(review);
    }

    @Override
    public void delete(Review review) {
        super.delete(review);
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
