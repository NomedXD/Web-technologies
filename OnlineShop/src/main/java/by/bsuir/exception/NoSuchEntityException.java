package by.bsuir.exception;

import lombok.Getter;

/**
 * Abstraction for other NoSuchObject exceptions
 */
@Getter
public abstract class NoSuchEntityException extends IllegalArgumentException {
    protected final Integer entityId;
    protected final String loggerMessage;

    /**
     * @param userMessage   displays on GUI
     * @param loggerMessage used by logger
     * @param entityId      additional saved entity id
     */
    public NoSuchEntityException(String userMessage, String loggerMessage, Integer entityId) {
        super(userMessage);
        this.loggerMessage = loggerMessage;
        this.entityId = entityId;
    }
}
