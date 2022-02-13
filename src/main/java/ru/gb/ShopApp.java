package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.HibernateConfig;
import ru.gb.dao.manufacturer.ManufacturerDao;
import ru.gb.dao.product.ProductDao;
import ru.gb.entity.Product;

public class ShopApp {

    public static void main(String[] args) {
//        //Output for the first implementation

//        OldJdbcProductDao oldJdbcProductDao = new OldJdbcProductDao();
//        for (Product product : oldJdbcProductDao.findAll()) {
//            System.out.println(product);
//        }

//        //Output for the second implementation

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
//        ProductDao productDao = context.getBean(ProductDao.class);
//        for (Product product : productDao.findAll()) {
//            System.out.println(product);
//        }

//        //Output for the third implementation

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
//        ProductDao productDao = context.getBean(ProductDao.class);
//        System.out.println(productDao.findNameById(15L));

//        //Output for the fourth implementation

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
//        ProductDao productDao = context.getBean(ProductDao.class);
//        System.out.println(productDao.findById(15L));

        //Output for the fifth implementation
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        System.out.println(productDao.findById(15L));
        System.out.println(productDao.findNameById(15L));
    }
}
