package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.Category;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private final EntityManager factory;
    @Autowired
    public CategoryRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public Category create(Category entity) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating category. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<Category> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Category", Category.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all categories. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Category update(Category entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating category. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            Category category = session.get(Category.class, id);
            session.remove(category);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting category. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<Category> findByName(String name) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from Category c where c.name =: name", Category.class).
                    setParameter("name", name).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting category by it's name. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Long getCountOfAllCategories() throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("select count(*) from Category", Long.class).getSingleResultOrNull();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting count of all categories. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Category> readOrderedByNameInRange(Integer currentPage, Integer pageSize) throws EntityOperationException {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Category c order by c.name", Category.class).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all paginated products in name order. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
