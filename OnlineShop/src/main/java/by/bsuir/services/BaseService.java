package by.bsuir.services;

import by.bsuir.domain.BaseEntity;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T create(T entity);

    List<T> read() throws SQLExecutionException;

    T update(T entity);

    void delete(Integer id);
}
