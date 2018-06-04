package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Review;
import com.borzdykooa.util.TestServiceDataImporter;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TestServiceDataImporter testServiceDataImporter;

    @Before
    public void initDb() {
        testServiceDataImporter.deleteTestData();
        testServiceDataImporter.importTestData();
    }

    @Test
    public void testFind() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r ", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);

        Review theSameReview = reviewService.find(review.getId());
        assertNotNull("Entity is null!", theSameReview);
    }

    @Test
    public void testFindAll() {
        List<Review> all = reviewService.findAll();
        List<Integer> collect = all.stream().map(Review::getMark).collect(toList());
        assertThat(collect, Matchers.hasItem(5));
    }

    @Test
    public void testDelete() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.mark=2 ", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);

        reviewService.delete(review);

        Review deletedReview = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.mark=2 ", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", deletedReview);
    }

    @Test
    public void testSave() {
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c ", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        Review review = new Review(3, "new comment", LocalDateTime.now(), client, medicine);
        Long id = reviewService.save(review);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testUpdate() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.mark=5 ", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);

        review.setMark(4);
        reviewService.update(review);

        Review updatedReview = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.mark=4 ", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);
        assertNotNull(updatedReview);
    }
}
