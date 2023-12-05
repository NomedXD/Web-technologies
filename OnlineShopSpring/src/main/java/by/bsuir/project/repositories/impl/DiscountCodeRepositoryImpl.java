package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.DiscountCode;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.DiscountCodeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class DiscountCodeRepositoryImpl implements DiscountCodeRepository {
    @PersistenceContext
    private final EntityManager factory;
    @Autowired
    public DiscountCodeRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public DiscountCode create(DiscountCode entity) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating discount code. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<DiscountCode> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from DiscountCode", DiscountCode.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all discount codes. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public DiscountCode update(DiscountCode entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating discount code. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            DiscountCode discountCode = session.get(DiscountCode.class, id);
            session.remove(discountCode);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting discount code. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<DiscountCode> findByName(String name) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from DiscountCode c where c.name =: name", DiscountCode.class).
                    setParameter("name", name).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting discount code by it's name. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<DiscountCode> findById(Integer id) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from DiscountCode c where c.id =: id", DiscountCode.class).
                    setParameter("id", id).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting discount code by it's id. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
