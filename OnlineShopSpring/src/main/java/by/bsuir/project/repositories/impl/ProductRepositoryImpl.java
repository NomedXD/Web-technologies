package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.Search;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.hibernate.query.Query;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private final EntityManager factory;

    public ProductRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public Product create(Product entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating product. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<Product> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Product", Product.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Product update(Product entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating product. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            Product product = session.get(Product.class, id);
            session.remove(product);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting product. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<Product> findById(Integer id) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from Product p where p.id =: id", Product.class).
                    setParameter("id", id).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting product by it's id. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Product> findAllByCategoryId(Integer categoryId) {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Product p where p.category.id =: id", Product.class).setParameter("id", categoryId).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all paginated user orders. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Product> findAllByCategoryIdOrderByName(Integer categoryId, Integer currentPage, Integer pageSize) {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Product p where p.category.id =: id order by p.name", Product.class).setParameter("id", categoryId).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all paginated category products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Product> findAllInOrderByName(Integer currentPage, Integer pageSize) {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Product p order by p.name", Product.class).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all paginated products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public List<Product> findAllSearchedInOrderByName(Search search, Integer currentPage, Integer pageSize) {
        try(Session session = factory.unwrap(Session.class)) {
            Query<Product> query = session.createQuery("from Product p where (p.name like :name or p.description like :description) and p.price >=: priceFrom and p.price <=: priceTo and p.category.name like :categoryName order by p.name", Product.class).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
            query.setParameter("name", !search.getSearchString().isBlank()? "%"+search.getSearchString()+"%" : "%%");
            query.setParameter("description", !search.getSearchString().isBlank()? "%"+search.getSearchString()+"%" : "%%");
            query.setParameter("priceFrom", search.getPriceFrom() != null && search.getPriceFrom() > 0 ? search.getPriceFrom() : -1);
            query.setParameter("priceTo", search.getPriceTo() != null && search.getPriceTo() > 0 ? search.getPriceFrom() : 1000000);
            query.setParameter("categoryName", !search.getCategoryName().isBlank()? "%"+search.getCategoryName()+"%" : "%%");
            return query.list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all searched paginated products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Long countAllByCategoryId(Integer categoryId) {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("select count(*) from Product", Long.class).getSingleResultOrNull();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting count of all products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Long countAll() {
        try(Session session = factory.unwrap(Session.class)) {
            return session.createQuery("select count(*) from Product", Long.class).getSingleResultOrNull();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting count of all products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Long countAllBySearch(Search search) {
        try(Session session = factory.unwrap(Session.class)) {
            Query<Long> query = session.createQuery("select count(*) from Product p where (p.name like :name or p.description like :description) and p.price >=: priceFrom and p.price <=: priceTo and p.category.name like :categoryName", Long.class);
            query.setParameter("name", !search.getSearchString().isBlank()? "%"+search.getSearchString()+"%" : "%%");
            query.setParameter("description", !search.getSearchString().isBlank()? "%"+search.getSearchString()+"%" : "%%");
            query.setParameter("priceFrom", search.getPriceFrom() != null && search.getPriceFrom() > 0 ? search.getPriceFrom() : -1);
            query.setParameter("priceTo", search.getPriceTo() != null && search.getPriceTo() > 0 ? search.getPriceFrom() : 1000000);
            query.setParameter("categoryName", !search.getCategoryName().isBlank()? "%"+search.getCategoryName()+"%" : "%%");
            return query.getSingleResultOrNull();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting count of all products. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
