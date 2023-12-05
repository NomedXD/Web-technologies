package by.bsuir.project.repositories;

import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.Search;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product> {
    Optional<Product> findById(Integer id);
    List<Product> findAllByCategoryId(Integer categoryId);
    List<Product> findAllByCategoryIdOrderByName(Integer categoryId, Integer currentPage, Integer pageSize);
    List<Product> findAllInOrderByName(Integer currentPage, Integer pageSize);
    List<Product> findAllSearchedInOrderByName(Search search, Integer currentPage, Integer pageSize);
    Long countAllByCategoryId(Integer categoryId);
    Long countAll();
    Long countAllBySearch(Search search);
}
