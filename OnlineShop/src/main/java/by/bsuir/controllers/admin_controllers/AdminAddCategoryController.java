package by.bsuir.controllers.admin_controllers;

import by.bsuir.enums.PagesPathEnum;
import by.bsuir.services.impl.AdminServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Admin servlet. Responsible for add category endpoints
 */
@WebServlet("/admin/add_category")
public class AdminAddCategoryController extends HttpServlet {
    AdminServiceImpl adminService = new AdminServiceImpl();

    /**
     * Dispatch an admin add_category page. If user does not log in dispatch a login page
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher(PagesPathEnum.LOG_IN_PAGE.getPath()).forward(req, resp);
        } else {
            adminService.getAddCategoryPage(req, resp);
        }
    }

    /**
     * Invoke service method to add category
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        adminService.addCategory(req, resp);
    }
}
