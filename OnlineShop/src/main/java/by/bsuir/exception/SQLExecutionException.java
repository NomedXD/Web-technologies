package by.bsuir.exception;

import java.sql.SQLException;

public class SQLExecutionException extends SQLException {
    public SQLExecutionException() {
        super("How did you get here. Check us later");
    }
}
