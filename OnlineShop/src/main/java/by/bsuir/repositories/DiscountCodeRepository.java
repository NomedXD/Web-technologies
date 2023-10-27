package by.bsuir.repositories;

import by.bsuir.domain.DiscountCode;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository extends BaseRepository {
    /**
     * @param entity entity to be persisted
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void create(DiscountCode entity) throws SQLExecutionException;

    /**
     * @return list of all entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<DiscountCode> read() throws SQLExecutionException;

    /**
     * @param entity entity to be updated
     * @return updated entity in database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    DiscountCode update(DiscountCode entity) throws SQLExecutionException;

    /**
     * @param id used to remove entity from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void delete(int id) throws SQLExecutionException;

    /**
     * @param name used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<DiscountCode> findDiscountCodeByName(String name) throws SQLExecutionException;
}
