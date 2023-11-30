package by.bsuir.project.controllers;

import by.bsuir.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/catalog")
public class HomeController {
    private final CategoryService categoryService;

    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getHomePage(@RequestParam(name = "page", required = false) Integer currentPage,
                                    @RequestParam(name = "size", required = false) Integer pageSize) {
        return categoryService.getPaginatedCategories(currentPage, pageSize);
    }
}
