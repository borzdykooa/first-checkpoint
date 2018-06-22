package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.ReviewDao;
import com.borzdykooa.entity.Review;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl extends BaseDaoImpl<Long, Review> implements ReviewDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Review> findAllByMedicineId(Long id) {
        return sessionFactory.getCurrentSession().createQuery("select r from Review r where r.reviewMedicine.id= :id", Review.class)
                .setParameter("id", id)
                .list();
    }
}