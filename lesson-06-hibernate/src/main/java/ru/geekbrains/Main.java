package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.Dao;
import ru.geekbrains.dao.ProductDaoImpl;
import ru.geekbrains.dao.UserDaoImpl;
import ru.geekbrains.persist.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final Dao<User> userDao = context.getBean("userDao", UserDaoImpl.class);
        final Dao<Product> productDao = context.getBean("productDao", ProductDaoImpl.class);

        System.out.println("#############################################################");
        System.out.println(" - create User");
        User sasha = new User(null, "Sasha", null);
        System.out.println(" - save User");
        userDao.save(sasha);
        System.out.println(sasha);

        System.out.println(" - create Product");
        Product laptop = new Product(null, "laptop", null);

        System.out.println(" - create Price");
        Price priceLaptop = new Price(null, laptop, new BigDecimal(1000), LocalDateTime.now(), null);
        System.out.println(" - set prices");
        laptop.setPrices(List.of(priceLaptop));
        System.out.println(" - save Product");
        productDao.save(laptop);
        System.out.println(laptop);

        System.out.println(" - create Order");
        Order order = new Order(null, LocalDateTime.now(), sasha, null);
        System.out.println(" - create Item");
        Item item = new Item(null, laptop, 1);
        System.out.println(" - set items");
        order.setItems(Set.of(item));
        System.out.println(" - set orders");
        sasha.setOrders(List.of(order));

        System.out.println(" - save User");
        userDao.save(sasha);

        System.out.println("=================================================================");
        sasha.getOrders().forEach(o -> {
            System.out.println("order.getDate() = " + o.getDate());
            System.out.println();
            o.getItems().forEach(it -> {
                System.out.println("item.getProduct().getName() = " + it.getProduct().getName());
                System.out.println("item.getProduct().getPrices().get(0).getValue() = " + it.getProduct().getPrices().get(0).getValue());
                System.out.println("item.it.getCount() = " + it.getCount());
            });
        });

    }
}
