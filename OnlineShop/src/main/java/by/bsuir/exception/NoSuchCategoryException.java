package by.bsuir.exception;

import lombok.Getter;

/**
 * Exception thrown out if there is no such category in database
 */
@Getter
public class NoSuchCategoryException extends NoSuchEntityException {
    public NoSuchCategoryException(Integer categoryId) {
        super("Product not found. How you even get there?",
                String.format("Category was not found by id %d. Check database", categoryId), categoryId);
    }
}
