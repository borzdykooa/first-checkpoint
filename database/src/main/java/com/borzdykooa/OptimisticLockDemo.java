package com.borzdykooa;

import com.borzdykooa.config.PersistenceDaoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class OptimisticLockDemo {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceDaoConfiguration.class);
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println();

    }
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
////        EmployeeTestDataImporter.getInstance().importTestData(sessionFactory);
//        try {
//            doSomeCode(sessionFactory);
//        } finally {
//            sessionFactory.close();
//        }
//    }
//
//    private static void doSomeCode(SessionFactory sessionFactory) {
//        try (Session firstSession = sessionFactory.openSession();
//             Session secondSession = sessionFactory.openSession()) {
//            firstSession.beginTransaction();
//            secondSession.beginTransaction();
//
//            SaleInfo saleInfo = firstSession.find(SaleInfo.class, 1L);
//            saleInfo.setQuantity(555L);
//
//            SaleInfo theSameSaleInfo = secondSession.find(SaleInfo.class, 1L);
//            theSameSaleInfo.setQuantity(111L);
//
//            firstSession.getTransaction().commit();
//
//            secondSession.getTransaction().commit();
//        }
//    }



    }
//}
