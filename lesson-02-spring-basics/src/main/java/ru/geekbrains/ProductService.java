package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.annotation.PostConstruct;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productRepository.save(new Product(null, "Product-1", 100));
        productRepository.save(new Product(null, "Product-2", 200));
        productRepository.save(new Product(null, "Product-3", 300));
        productRepository.save(new Product(null, "Product-4", 400));
        productRepository.save(new Product(null, "Product-5", 500));
    }

    public long count() {
        return productRepository.findAll().size();
    }
}
