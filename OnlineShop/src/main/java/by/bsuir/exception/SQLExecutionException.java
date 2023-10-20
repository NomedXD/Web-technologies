package by.bsuir.exception;

import java.sql.SQLException;

public class SQLExecutionException extends SQLException {
    public SQLExecutionException(String message) {
        super(message);
    }
}
