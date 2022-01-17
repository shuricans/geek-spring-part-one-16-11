package ru.geekbrains.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.persist.Order;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class OrderDaoImpl implements OrderDao, ExecuteForEntityManager {

    private final EntityManagerFactory emFactory;

    @Override
    public Optional<Order> findById(long id) {
        return executeForEntityManager(
                emFactory,
                em -> Optional.ofNullable(em.find(Order.class, id))
        );
    }

    @Override
    public List<Order> findAll() {
        return executeForEntityManager(
                emFactory,
                em -> em.createQuery("from Order o", Order.class)
                        .getResultList()
        );
    }

    @Override
    public void save(Order order) {
        executeInTransaction(emFactory, em -> {
            if (order.getId() == null) {
                em.persist(order);
            } else {
                em.merge(order);
            }
        });
    }

    @Override
    public void delete(Order order) {
        executeInTransaction(emFactory, em -> em.remove(order));
    }
}
