package by.bsuir.project.controllers.admin_controllers;

import by.bsuir.project.domain.Category;
import by.bsuir.project.domain.Image;
import by.bsuir.project.domain.Product;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.services.impl.AdminServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin/add_product")
public class AdminAddProductController {
    AdminServiceImpl adminService;

    @Autowired
    public AdminAddProductController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ModelAndView getAddProductPage() {
        return new ModelAndView(PagesPathEnum.ADMIN_ADD_PRODUCT_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView addProduct(Product product, @RequestParam(name = "categoryName", required = false) String categoryName,
                                   @RequestParam(name = "imagePath", required = false) String imagePath) {
        product.setImages(new ArrayList<>());
        product.getImages().add(Image.builder().path("images/" + imagePath).isPrime(false).build());
        return adminService.addProduct(product, categoryName);
    }
}
