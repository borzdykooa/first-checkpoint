package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Long, Review> {

    List<Review> findAllByMedicineId(Long id);
}
