package by.bsuir.project.repositories.impl;

import by.bsuir.project.domain.Image;
import by.bsuir.project.exception.EntityOperationException;
import by.bsuir.project.repositories.ImageRepository;
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
public class ImageRepositoryImpl implements ImageRepository {
    @PersistenceContext
    private final EntityManager factory;

    @Autowired
    public ImageRepositoryImpl(EntityManager factory) {
        this.factory = factory;
    }

    @Override
    public Image create(Image entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            session.persist(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while creating image. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
        return entity;
    }

    @Override
    public List<Image> read() throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.createQuery("from Image", Image.class).list();
        } catch (PersistenceException e) {
            log.warn("SQLException while getting all images. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Image update(Image entity) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            return session.merge(entity);
        } catch (PersistenceException e) {
            log.warn("SQLException while updating image. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public void delete(Integer id) throws EntityOperationException {
        try (Session session = factory.unwrap(Session.class)) {
            Image image = session.get(Image.class, id);
            session.remove(image);
        } catch (PersistenceException e) {
            log.warn("SQLException while deleting image. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }

    @Override
    public Optional<Image> findById(Integer id) {
        try (Session session = factory.unwrap(Session.class)) {
            return Optional.of(session.createQuery("from Image i where i.id =: id", Image.class).
                    setParameter("id", id).getSingleResultOrNull());
        } catch (PersistenceException e) {
            log.warn("SQLException while getting image by it's id. Most likely request is wrong. Full message - " + e.getMessage());
            throw new EntityOperationException("Unexpected error on the site. How do you get here?\nCheck us later");
        }
    }
}
