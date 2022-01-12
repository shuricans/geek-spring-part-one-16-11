package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryHibernateImpl implements ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepositoryHibernateImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(product);
    }

    @Override
    public void save(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Product p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
