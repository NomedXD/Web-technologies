package by.bsuir.services.impl;

import by.bsuir.domain.Category;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.CategoryRepository;
import by.bsuir.repositories.impl.CategoryRepositoryImpl;
import by.bsuir.services.CategoryService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public Category create(Category entity) {
        return null;
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.SHOP_PAGE.getPath());
            requestDispatcher.forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return Optional.empty();
    }


}
