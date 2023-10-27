package by.bsuir.services.impl;

import by.bsuir.domain.Category;
import by.bsuir.domain.Product;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.services.CategoryService;
import by.bsuir.services.ProductService;
import by.bsuir.utils.ValidatorUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class AdminServiceImpl {
    CategoryService categoryService = new CategoryServiceImpl();
    ProductService productService = new ProductServiceImpl();
    public void getAddCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_CATEGORY_PAGE.getPath()).forward(request, response);
    }

    public void getAddProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_PRODUCT_PAGE.getPath()).forward(request, response);
    }

    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String someText = request.getParameter("someText");
        String imagePath = "images/" + request.getParameter("imagePath");
        try {
            if (ValidatorUtils.validateNewCategoryData(name, someText, imagePath)) {
                categoryService.create(Category.builder().name(name).someText(someText).imagePath(imagePath).build());
                request.setAttribute("stateMessage", "Категория добавлена");
            } else {
                request.setAttribute("stateMessage", "Неверные данные");
            }
            request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_CATEGORY_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String categoryName = request.getParameter("categoryName");
        String price = request.getParameter("price");
        String imagePath = "images/" + request.getParameter("imagePath");
        try {
            Optional<Category> category = categoryService.getCategoryByName(categoryName);
            if (ValidatorUtils.validateNewProduct(name, price, description, imagePath) && category.isPresent()) {
                productService.create(Product.builder().name(name)
                        .description(description)
                        .categoryId(category.get().getId())
                        .price(Float.parseFloat(price))
                        .imagePath(imagePath).build());
                request.setAttribute("stateMessage", "Продукт добавлен");
            } else {
                request.setAttribute("stateMessage", "Неверные данные");
            }
            request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_PRODUCT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
