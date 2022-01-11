package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryHibernateImpl;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductRepository productRepository = new ProductRepositoryHibernateImpl(emFactory);

        Product laptopDell = new Product("laptop", "dell", new BigDecimal(1000));
        productRepository.save(laptopDell);

        System.out.println(productRepository.findAll());
    }
}
