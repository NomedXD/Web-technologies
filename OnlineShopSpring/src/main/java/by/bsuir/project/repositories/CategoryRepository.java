package by.bsuir.project.repositories;

import by.bsuir.project.domain.Category;
import by.bsuir.project.exception.EntityOperationException;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category> {
    Category findByName(String name);

    Long getCountOfAllCategories() throws EntityOperationException;

    List<Category> readOrderedByNameInRange(Integer currentPage, Integer count) throws EntityOperationException;
}