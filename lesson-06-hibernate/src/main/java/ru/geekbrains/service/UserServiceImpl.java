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

    private final UserDao userDao;
    private final OrderDao orderDao;

    // "Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров"
    // "Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;"
    public List<Product> getProductsByUserId(long userId) {
        final List<Product> products = new ArrayList<>();
        userDao.findById(userId)
                .ifPresentOrElse(user -> user.getOrders().forEach(order ->
                        order.getItems().forEach(item -> {
                            Product product = item.getProduct();
                            // сколько стоил товар в момент покупки клиентом
                            product.setPrice(item.getPrice());
                            products.add(product);
                        })), () -> {
                    throw new RuntimeException("User with id = " + userId + "does not exist.");
                });
        return products;
    }

    // "по id товара узнавать список покупателей этого товара"
    @Override
    public List<User> getCustomersByProductId(long productId) {
        List<User> customers = new ArrayList<>();
        orderDao.findAll().forEach(order -> {
            boolean isPresent = order.getItems().stream()
                    .anyMatch(item -> item.getId().equals(productId));
            if (isPresent) {
                customers.add(order.getUser());
            }
        });
        return customers;
    }
}
