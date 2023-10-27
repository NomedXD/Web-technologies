package by.bsuir.services;

import by.bsuir.domain.Category;
import by.bsuir.exception.SQLExecutionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface CategoryService extends BaseService<Category> {
    /**
     * Returns the main page.
     * <p>
     * A list of all categories is placed in the request parameters
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void getHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * @param id used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<Category> getCategoryById(Integer id) throws SQLExecutionException;

    /**
     * @param name used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<Category> getCategoryByName(String name) throws SQLExecutionException;
}
