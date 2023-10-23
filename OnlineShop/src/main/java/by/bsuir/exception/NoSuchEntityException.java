package by.bsuir.exception;

import lombok.Getter;

@Getter
public abstract class NoSuchEntityException extends IllegalArgumentException{
    protected final Integer entityId;
    protected final String loggerMessage;
    public NoSuchEntityException(String userMessage, String loggerMessage, Integer entityId) {
        super(userMessage);
        this.loggerMessage = loggerMessage;
        this.entityId = entityId;
    }
}
