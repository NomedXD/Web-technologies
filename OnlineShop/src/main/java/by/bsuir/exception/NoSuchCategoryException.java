package by.bsuir.exception;

import lombok.Getter;

@Getter
public class NoSuchCategoryException extends NoSuchEntityException {
    public NoSuchCategoryException(Integer categoryId) {
        super("Product not found. How you even get there?",
                String.format("Category was not found by id %d. Check database", categoryId), categoryId);
    }
}
