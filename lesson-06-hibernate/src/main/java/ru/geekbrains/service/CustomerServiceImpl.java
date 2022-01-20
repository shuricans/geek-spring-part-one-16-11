package ru.geekbrains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dao.ExecuteForEntityManager;
import ru.geekbrains.persist.Customer;
import ru.geekbrains.persist.Item;
import ru.geekbrains.persist.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService, ExecuteForEntityManager {

    private final EntityManagerFactory emFactory;

    // "по id покупателя узнать список купленных им товаров"
    // "Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;"
    @Override
    public List<Product> getProductsByCustomerId(long customerId) {
        final List<Item> items = executeForEntityManager(
                emFactory,
                em -> em.createQuery("" +
                                "select i from Item i" +
                                " join i.order o" +
                                " join o.customer c" +
                                " where c.id = :customerId", Item.class)
                        .setParameter("customerId", customerId)
                        .getResultList());

        // сколько стоил товар в момент покупки клиентом
        return items.stream()
                .map(item -> {
                    Product product = item.getProduct();
                    product.setPrice(item.getPrice());
                    return product;
                }).collect(Collectors.toList());
    }

    // "по id товара узнавать список покупателей этого товара"
    @Override
    public List<Customer> getCustomersByProductId(long productId) {
        return executeForEntityManager(
                emFactory,
                em -> em.createQuery("" +
                                "select c from Customer c" +
                                " join c.orders o" +
                                " join o.items i" +
                                " join i.product p" +
                                " where p.id = :productId", Customer.class)
                        .setParameter("productId", productId)
                        .getResultList()
        );
    }
}
