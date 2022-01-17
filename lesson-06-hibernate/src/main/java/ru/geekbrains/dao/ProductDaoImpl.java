package ru.geekbrains.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.persist.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProductDaoImpl implements ProductDao, ExecuteForEntityManager {

    private final EntityManagerFactory emFactory;

    @Override
    public Optional<Product> findById(long id) {
        return executeForEntityManager(
                emFactory,
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    @Override
    public List<Product> findAll() {
        return executeForEntityManager(
                emFactory,
                em -> em.createQuery("from Product p", Product.class)
                        .getResultList()
        );
    }

    @Override
    public void save(Product product) {
        executeInTransaction(emFactory, em -> {
            if (product.getId() == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
        });
    }

    @Override
    public void delete(Product product) {
        executeInTransaction(emFactory, em -> em.remove(product));
    }
}
