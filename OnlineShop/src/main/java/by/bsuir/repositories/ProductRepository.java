package by.bsuir.repositories;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product>{
    List<Product> getProductsByCategoryId(Integer categoryId) throws SQLExecutionException;
}
