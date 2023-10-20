package by.bsuir.repositories;

import by.bsuir.dbconnection.ConnectionPool;

public interface BaseRepository {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
}
