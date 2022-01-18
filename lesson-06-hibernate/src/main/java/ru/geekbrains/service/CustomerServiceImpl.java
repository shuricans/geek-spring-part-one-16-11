package ru.geekbrains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dao.ExecuteForEntityManager;
import ru.geekbrains.persist.Customer;
import ru.geekbrains.persist.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService, ExecuteForEntityManager {

    private final EntityManagerFactory emFactory;

    // "по id покупателя узнать список купленных им товаров"
    // "Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;"
    @Override
    public List<Product> getProductsByCustomerId(long customerId) {
        return null;
    }

    // "по id товара узнавать список покупателей этого товара"
    @Override
    public List<Customer> getCustomersByProductId(long productId) {
        return executeForEntityManager(
                emFactory,
                em -> em.createQuery("" +
                                "select c from Customer c " +
                                "join c.orders o " +
                                "join o.items i " +
                                "join i.product p " +
                                "where p.id = :productId", Customer.class)
                        .setParameter("productId", productId)
                        .getResultList()
        );
    }
}
