package com.borzdykooa.service;

import com.borzdykooa.dao.repository.ReviewDao;
import com.borzdykooa.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public Review find(Long id) {
        return reviewDao.find(id);
    }

    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    public Long save(Review review) {
        return reviewDao.save(review);
    }

    public void delete(Review review) {
        reviewDao.delete(review);
    }

    public void update(Review review) {
        reviewDao.update(review);
    }

    public List<Review> findAllByMedicineId(Long id) {
        return reviewDao.findAllByMedicineId(id);
    }
}
