package ru.geekbrains.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
@Component
public class UserDaoImpl implements Dao<User> {

    private final EntityManagerFactory emFactory;

    @Override
    public Optional<User> findById(long id) {
        return executeForEntityManager(
                emFactory,
                em -> Optional.ofNullable(em.find(User.class, id))
        );
    }

    @Override
    public List<User> findAll() {
        return executeForEntityManager(
                emFactory,
                em -> em.createQuery("from User u", User.class)
                        .getResultList()
        );
    }

    @Override
    public void save(User user) {
        executeInTransaction(emFactory, em -> {
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
        });
    }

    @Override
    public void delete(User user) {
        executeInTransaction(emFactory, em -> em.remove(user));
    }
}
