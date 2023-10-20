package by.bsuir.repositories;

import by.bsuir.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category>{
    Optional<Category> getCategoryById(Integer id);
}
