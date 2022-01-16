package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.Dao;
import ru.geekbrains.dao.UserDaoImpl;
import ru.geekbrains.persist.User;

public class Main {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final Dao<User> userDao = context.getBean("userDaoImpl", UserDaoImpl.class);

        User sasha = new User(null, "Sasha", null);
        System.out.println(sasha);
    }
}
