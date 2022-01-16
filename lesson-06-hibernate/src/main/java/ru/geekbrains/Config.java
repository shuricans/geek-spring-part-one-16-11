package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.dao.UserDaoImpl;

import javax.persistence.EntityManagerFactory;

@ComponentScan("ru.geekbrains")
@Configuration
public class Config {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Bean
    public UserDaoImpl userDaoImpl() {
        return new UserDaoImpl(entityManagerFactory());
    }


}
