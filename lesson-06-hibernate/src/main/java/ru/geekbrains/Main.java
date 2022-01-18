package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.persist.*;
import ru.geekbrains.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        EntityManagerFactory emFactory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
//
//        EntityManager em = emFactory.createEntityManager();
//        em.getTransaction().begin();
//
//
//
//        em.getTransaction().commit();
//        em.close();

        final UserDao userDao = context.getBean(UserDao.class);
        final ProductDao productDao = context.getBean(ProductDao.class);

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

//        final ProductDao productDao = context.getBean(ProductDao.class);
//        final UserService userService = context.getBean(UserService.class);

//        User newUser = new User(null, "new user", null);
//        userDao.save(newUser);

//        List<Product> products = productDao.findAll();
//        List<User> users = userDao.findAll();
//
//        User user1 = users.get(0);
//        User user2 = users.get(1);
//
//
//        Order order = new Order(null, LocalDateTime.now(), user1, null);
//
//        Product p1 = products.get(0);
//        Product p2 = products.get(1);
//        Item item1 = new Item(null, p1, 2, p1.getPrice(), null);
//        Item item2 = new Item(null, p2, 5, p2.getPrice(), null);
//        order.setItems(Set.of(item1, item2));
//
//
//        user1.setOrders(List.of(order));
//
//        userDao.save(user1);


    }
}
