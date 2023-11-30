package by.bsuir.project.exception;

public class NoSuchUserException extends IllegalArgumentException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
