package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.persist.*;
import ru.geekbrains.service.CustomerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final UserDao userDao = context.getBean(UserDao.class);
        final ProductDao productDao = context.getBean(ProductDao.class);
        final CustomerService customerService = context.getBean(CustomerService.class);

        User user = new User(null, "sasha", null);
        user.setCustomer(new Customer(null, user, null));

        Product productLaptop = new Product(null, "laptop", new BigDecimal(1000));
        Product productPhone = new Product(null, "iphone", new BigDecimal(700));
        productDao.save(productLaptop);
        productDao.save(productPhone);


        Order order = new Order(null, LocalDateTime.now(), user.getCustomer(), null);
        Item item1 = new Item(null, productLaptop, 1, productLaptop.getPrice(), order);
        Item item2 = new Item(null, productPhone, 1, productPhone.getPrice(), order);
        order.setItems(Set.of(item1, item2));
        user.getCustomer().setOrders(List.of(order));

        userDao.save(user);

        List<Customer> customers = customerService.getCustomersByProductId(1L);
        System.out.println("customers.size() = " + customers.size());
        System.out.println(customers);

        System.out.println(customerService.getProductsByCustomerId(1L));
    }
}
