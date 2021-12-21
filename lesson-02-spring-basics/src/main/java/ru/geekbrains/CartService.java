package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.ProductRepository;

public class CartService {

    @Autowired
    private ProductRepository productRepository;

    // TODO
}
