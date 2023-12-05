package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.Order;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private final EntityManager factory;

    public OrderRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public Order create(Order entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating order. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<Order> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Order", Order.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all orders. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Order update(Order entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating order. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            Order order = session.get(Order.class, id);
            session.remove(order);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting order. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Long countAllByUserId(Integer userId) {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("select count(*) from Order o where o.user.id =: id", Long.class).setParameter("id", userId).getSingleResultOrNull();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting count of all orders. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Order> findAllByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Order o where o.user.id =: id", Order.class).setParameter("id", userId).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all paginated user orders. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
