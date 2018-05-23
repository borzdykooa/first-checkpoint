package com.borzdykooa;

import com.borzdykooa.entity.SaleInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OptimisticLockDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        EmployeeTestDataImporter.getInstance().importTestData(sessionFactory);
        try {
            doSomeCode(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void doSomeCode(SessionFactory sessionFactory) {
        try (Session firstSession = sessionFactory.openSession();
             Session secondSession = sessionFactory.openSession()) {
            firstSession.beginTransaction();
            secondSession.beginTransaction();

            SaleInfo saleInfo = firstSession.find(SaleInfo.class, 1L);
            saleInfo.setQuantity(555L);

            SaleInfo theSameSaleInfo = secondSession.find(SaleInfo.class, 1L);
            theSameSaleInfo.setQuantity(111L);

            firstSession.getTransaction().commit();

            secondSession.getTransaction().commit();
        }
    }

}

