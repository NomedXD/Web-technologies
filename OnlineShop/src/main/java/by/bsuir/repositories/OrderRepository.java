package by.bsuir.repositories;

import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface OrderRepository extends BaseRepository {
    /**
     * @param entity entity to be persisted
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void create(Order entity) throws SQLExecutionException;

    /**
     * @return list of all entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Order> read() throws SQLExecutionException;

    /**
     * @param entity entity to be updated
     * @return updated entity in database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Order update(Order entity) throws SQLExecutionException;

    /**
     * @param id used to remove entity from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void delete(int id) throws SQLExecutionException;

    /**
     * @param userId used to find entity in database
     * @return list of founded entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Order> findAllByUserId(Integer userId) throws SQLExecutionException;
}
