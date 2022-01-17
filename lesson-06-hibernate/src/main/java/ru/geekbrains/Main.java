package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.persist.Item;
import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;
import ru.geekbrains.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final UserDao userDao = context.getBean(UserDao.class);
        final ProductDao productDao = context.getBean(ProductDao.class);
        final UserService userService = context.getBean(UserService.class);

        System.out.println("#############################################################");
        System.out.println(" - create User Sasha");
        User sasha = new User(null, "Sasha", null);
        System.out.println(" - save User Sasha");
        userDao.save(sasha);
        System.out.println(sasha);

        System.out.println(" - create User Anya");
        User anya = new User(null, "Anya", null);
        System.out.println(" - save User Anya");
        userDao.save(anya);
        System.out.println(anya);

        System.out.println(" - create Product-11");
        Product laptop = new Product(null, "laptop", new BigDecimal(1000));
        System.out.println(" - save Product-1");
        productDao.save(laptop);
        System.out.println(laptop);

        System.out.println(" - create Product-2");
        Product phone = new Product(null, "onePlus", new BigDecimal(800));
        System.out.println(" - save Product-2");
        productDao.save(phone);
        System.out.println(phone);


        System.out.println(" - create Order");
        Order order = new Order(null, LocalDateTime.now(), sasha, null);
        System.out.println(" - create Item - 1");
        Item item1 = new Item(null, laptop, 1, laptop.getPrice(), order);
        System.out.println(" - create Item - 2");
        Item item2 = new Item(null, phone, 1, phone.getPrice(), order);
        System.out.println(" - set items");
        order.setItems(Set.of(item1, item2));
        System.out.println(" - set orders");
        sasha.setOrders(List.of(order));

        System.out.println(" - save User");
        userDao.save(sasha);

        Optional<User> optionalUser = userDao.findById(1L);
        User user = optionalUser.get();
        System.out.println("user = " + user);

        System.out.println("------------------------------------------------");
        userService.getProductsByUserId(1L).forEach(System.out::println);
        System.out.println("------------------------------------------------");
        userService.getCustomersByProductId(2L).forEach(System.out::println);

        //        List<Order> orders = user.getOrders();
//        orders.forEach(o -> {
//            o.getItems().forEach(item -> {
//                System.out.println("item.getProduct().getName() = " + item.getProduct().getName());
//                System.out.println("item.getPrice() = " + item.getPrice());
//            });
//        });


//        System.out.println("=================================================================");
//        sasha.getOrders().forEach(o -> {
//            System.out.println("order.getDate() = " + o.getDate());
//            System.out.println();
//            o.getItems().forEach(it -> {
//                System.out.println("item.getProduct().getName() = " + it.getProduct().getName());
//                System.out.println("item.getProduct().getPrice() = " + it.getProduct().getPrice());
//                System.out.println("item.getCount() = " + it.getCount());
//            });
//        });

//        userProductService.getProductsByUserId(1L).forEach(System.out::println);
//        userService.getProductsByUserId(1L).forEach(System.out::println);

    }
}
