package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryHibernateImpl;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        final ProductRepository productRepository = new ProductRepositoryHibernateImpl(emFactory);

        // === save ===
        System.out.println("=== save ===");
        Product laptopDell = new Product("laptop", "dell", new BigDecimal(1000));
        productRepository.save(laptopDell);

        // === findAll ===
        System.out.println("=== findAll ===");
        List<Product> products = productRepository.findAll();
        System.out.println(products);

        // === findById ===
        System.out.println("=== findById ===");
        long id = 1L;
        Optional<Product> productById = productRepository.findById(id);
        productById.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("product with id = " + id + ", not found.")
        );

        // === delete ===
        System.out.println("=== delete ===");
        productRepository.delete(1L);
        products = productRepository.findAll();
        System.out.println(products);
    }
}
