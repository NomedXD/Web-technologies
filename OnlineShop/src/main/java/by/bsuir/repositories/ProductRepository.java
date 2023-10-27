package by.bsuir.repositories;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository {
    /**
     * @param entity entity to be persisted
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void create(Product entity) throws SQLExecutionException;

    /**
     * @return list of all entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Product> read() throws SQLExecutionException;

    /**
     * @param entity entity to be updated
     * @return updated entity in database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Product update(Product entity) throws SQLExecutionException;

    /**
     * @param id used to remove entity from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void delete(int id) throws SQLExecutionException;

    /**
     * @param categoryId used to find entity in database
     * @return list of founded entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Product> getProductsByCategoryId(Integer categoryId) throws SQLExecutionException;

    /**
     * @param productId used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<Product> findById(Integer productId) throws SQLExecutionException;
}
