package ru.geekbrains.service;

import ru.geekbrains.persist.Customer;
import ru.geekbrains.persist.Product;

import java.util.List;

public interface CustomerService {
    List<Product> getProductsByCustomerId(long customerId);
    List<Customer> getCustomersByProductId(long productId);
}
