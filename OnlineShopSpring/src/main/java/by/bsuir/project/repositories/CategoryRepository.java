package by.bsuir.project.repositories;

import by.bsuir.project.domain.Category;
import by.bsuir.project.exception.EntityOperationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends BaseRepository<Category>{
    Category getCategoryByName(String name);
    Long getCountOfAllCategories() throws EntityOperationException;
    List<Category> readOrderedByNameInRange(Integer currentPage, Integer count) throws EntityOperationException;
}