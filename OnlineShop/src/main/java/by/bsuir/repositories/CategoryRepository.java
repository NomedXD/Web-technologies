package by.bsuir.repositories;

import by.bsuir.domain.Category;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseRepository {
    Category create(Category entity);
    List<Category> read() throws SQLExecutionException;
    Category update(Category entity);
    void delete(int id);
    Optional<Category> getCategoryById(Integer id);
}
