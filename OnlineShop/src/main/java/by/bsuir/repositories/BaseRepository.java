package by.bsuir.repositories;

import by.bsuir.dbconnection.ConnectionPool;
import by.bsuir.domain.BaseEntity;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    T create(T entity);

    List<T> read() throws SQLExecutionException;

    T update(T entity);

    void delete(int id);
}
