package ru.geekbrains.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ExecuteForEntityManager {

    default <R> R executeForEntityManager(EntityManagerFactory emFactory, Function<EntityManager, R> function) {
        final EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    default void executeInTransaction(EntityManagerFactory emFactory, Consumer<EntityManager> consumer) {
        final EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
