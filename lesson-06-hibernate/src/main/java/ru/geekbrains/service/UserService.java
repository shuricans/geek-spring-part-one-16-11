package ru.geekbrains.service;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;

import java.util.List;

public interface UserService {
    List<Product> getProductsByUserId(long userId);
    List<User> getCustomersByProductId(long productId);
}
