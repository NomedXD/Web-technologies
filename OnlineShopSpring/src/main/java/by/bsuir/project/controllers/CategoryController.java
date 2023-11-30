package by.bsuir.project.controllers;

import by.bsuir.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ProductService productService;

    @Autowired
    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{categoryId}")
    public ModelAndView getCategoryPage(@PathVariable(name = "categoryId") Integer categoryId,
                                        @RequestParam(name = "page", required = false) Integer currentPage,
                                        @RequestParam(name = "size", required = false) Integer pageSize) {
        return productService.getPaginatedProductsByCategoryId(categoryId, currentPage, pageSize);
    }
}
