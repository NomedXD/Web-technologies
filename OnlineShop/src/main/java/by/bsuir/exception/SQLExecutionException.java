package by.bsuir.exception;

import java.sql.SQLException;

/**
 * Exception thrown out if occurs exception via database operation
 */
public class SQLExecutionException extends SQLException {
    public SQLExecutionException() {
        super("How did you get here. Check us later");
    }
}
