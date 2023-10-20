package by.bsuir.repositories;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface ProductRepository extends BaseRepository {
    Product create(Product entity);
    List<Product> read();
    Product update(Product entity);
    void delete(int id);
    List<Product> getProductsByCategoryId(Integer categoryId) throws SQLExecutionException;
}
