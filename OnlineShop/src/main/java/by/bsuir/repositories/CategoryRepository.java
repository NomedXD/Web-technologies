package by.bsuir.repositories;

import by.bsuir.domain.Category;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseRepository {
    void create(Category entity) throws SQLExecutionException;
    List<Category> read() throws SQLExecutionException;
    Category update(Category entity);
    void delete(int id);
    Optional<Category> findById(Integer categoryId) throws SQLExecutionException;
    Optional<Category> findByName(String categoryName) throws SQLExecutionException;
}
