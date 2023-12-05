package by.bsuir.project.controllers.admin_controllers;

import by.bsuir.project.domain.Category;
import by.bsuir.project.domain.Image;
import by.bsuir.project.domain.User;
import by.bsuir.project.enums.EshopConstants;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.services.impl.AdminServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Admin servlet. Responsible for add category endpoints
 */
@Controller
@RequestMapping("/admin/add_category")
public class AdminAddCategoryController {
    AdminServiceImpl adminService;

    @Autowired
    public AdminAddCategoryController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ModelAndView getAddCategoryPage() {
        return new ModelAndView(PagesPathEnum.ADMIN_ADD_CATEGORY_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView addCategory(Category category, @RequestParam(name = "imagePath", required = false) String imagePath) {
        category.setImages(new ArrayList<>());
        category.getImages().add(Image.builder().path("images/" + imagePath).isPrime(false).build());
        return adminService.addCategory(category);
    }
}
