package by.bsuir.repositories;

import by.bsuir.domain.DiscountCode;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository extends BaseRepository{
    DiscountCode create(DiscountCode entity);
    List<DiscountCode> read() throws SQLExecutionException;
    DiscountCode update(DiscountCode entity);
    void delete(int id);
    Optional<DiscountCode> findDiscountCodeByName(String name) throws SQLExecutionException;
}
