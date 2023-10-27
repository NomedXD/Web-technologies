package by.bsuir.repositories;

import by.bsuir.domain.Category;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseRepository {
    /**
     * @param entity entity to be persisted
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void create(Category entity) throws SQLExecutionException;

    /**
     * @return list of all entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Category> read() throws SQLExecutionException;

    /**
     * @param entity entity to be updated
     * @return updated entity in database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Category update(Category entity) throws SQLExecutionException;

    /**
     * @param id used to remove entity from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void delete(int id) throws SQLExecutionException;

    /**
     * @param categoryId used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<Category> findById(Integer categoryId) throws SQLExecutionException;

    /**
     * @param categoryName used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<Category> findByName(String categoryName) throws SQLExecutionException;
}
