package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.service.UserService;

public class Main {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final UserDao userDao = context.getBean(UserDao.class);
        final ProductDao productDao = context.getBean(ProductDao.class);
        final UserService userService = context.getBean(UserService.class);

    }
}
