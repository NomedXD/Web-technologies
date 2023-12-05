package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.Image;
import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.User;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager factory;

    public UserRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public User create(User entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating user. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<User> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from User", User.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all users. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public User update(User entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating user. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            User user = session.get(User.class, id);
            session.remove(user);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting user. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<User> findUserByMail(String mail) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from User u where u.mail =: mail", User.class).
                    setParameter("mail", mail).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting user by it's mail. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from User u where u.id =: id", User.class).
                    setParameter("id", id).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting user by it's id. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Order> findOrdersByUserId(Integer id) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Order o where o.user.id =: id", Order.class).setParameter("id", id).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting user orders. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
