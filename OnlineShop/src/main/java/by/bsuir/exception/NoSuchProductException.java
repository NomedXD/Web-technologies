package by.bsuir.exception;

import lombok.Getter;

@Getter
public class NoSuchProductException extends NoSuchEntityException{
    public NoSuchProductException(Integer productId) {
        super("Product not found. How you even get there?",
                String.format("Product was not found by id %d. Check database", productId), productId);;
    }
}
