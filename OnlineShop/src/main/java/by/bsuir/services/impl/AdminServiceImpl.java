package by.bsuir.services.impl;

import by.bsuir.enums.PagesPathEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminServiceImpl {
    public void getAddCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_CATEGORY_PAGE.getPath()).forward(request, response);
    }

    public void getAddProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagesPathEnum.ADMIN_ADD_PRODUCT_PAGE.getPath()).forward(request, response);
    }
}
