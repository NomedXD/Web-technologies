package by.bsuir.services;

import by.bsuir.domain.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface CategoryService extends BaseService<Category>{
    void getHomePage(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
    Optional<Category> getCategoryById(Integer id);
}
