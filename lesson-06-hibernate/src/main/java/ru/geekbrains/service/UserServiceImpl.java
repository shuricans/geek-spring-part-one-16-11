package ru.geekbrains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dao.OrderDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {



    // "Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров"
    // "Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;"
    public List<Product> getProductsByUserId(long userId) {
        final List<Product> products = new ArrayList<>();

        return products;
    }

    // "по id товара узнавать список покупателей этого товара"
    @Override
    public List<User> getCustomersByProductId(long productId) {
        List<User> customers = new ArrayList<>();

        return customers;
    }
}
