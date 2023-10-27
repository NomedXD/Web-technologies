package by.bsuir.services;

import by.bsuir.domain.BaseEntity;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    void create(T entity) throws SQLExecutionException;

    List<T> read() throws SQLExecutionException;

    T update(T entity);

    void delete(Integer id);
}
