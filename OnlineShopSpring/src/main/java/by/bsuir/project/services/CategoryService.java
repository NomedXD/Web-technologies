package by.bsuir.project.services;

import by.bsuir.project.domain.Category;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface CategoryService extends BaseService<Category>{
    Optional<Category> getCategoryByName(String name);

    ModelAndView getPaginatedCategories(Integer currentPage, Integer pageSize);
}
