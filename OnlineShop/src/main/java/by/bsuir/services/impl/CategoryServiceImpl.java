package by.bsuir.services.impl;

import by.bsuir.domain.Category;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.CategoryRepository;
import by.bsuir.repositories.impl.CategoryRepositoryImpl;
import by.bsuir.services.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public void create(Category entity) throws SQLExecutionException {
        categoryRepository.create(entity);
    }

    @Override
    public List<Category> read() throws SQLExecutionException {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void getHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("categories", read());
            request.getRequestDispatcher(PagesPathEnum.SHOP_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) throws SQLExecutionException {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) throws SQLExecutionException {
        return categoryRepository.findByName(name);
    }
}
