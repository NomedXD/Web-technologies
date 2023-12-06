package by.bsuir.project.services.impl;

import by.bsuir.project.domain.Category;
import by.bsuir.project.domain.Product;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.services.CategoryService;
import by.bsuir.project.services.ProductService;
import by.bsuir.project.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl {
    CategoryService categoryService;
    ProductService productService;

    @Autowired
    public AdminServiceImpl(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    public ModelAndView addCategory(Category category) {
        ModelMap model = new ModelMap();
        if (ValidatorUtils.validateNewCategoryData(category.getName(), category.getSometext(), category.getImages().get(0).getPath())) {
            categoryService.create(Category.builder().name(category.getName()).sometext(category.getSometext()).images(category.getImages()).build());
            model.addAttribute("stateMessage", "Категория добавлена");
        } else {
            model.addAttribute("stateMessage", "Неверные данные");
        }
        return new ModelAndView(PagesPathEnum.ADMIN_ADD_CATEGORY_PAGE.getPath(), model);
    }

    public ModelAndView addProduct(Product product, String categoryName) {
        Optional<Category> category = categoryService.getCategoryByName(categoryName);
        ModelMap modelMap = new ModelMap();
        if (ValidatorUtils.validateNewProduct(product.getName(), product.getPrice(), product.getDescription(), product.getImages().get(0).getPath()) && category.isPresent()) {
            productService.create(Product.builder().name(product.getName())
                    .description(product.getDescription())
                    .category(category.get())
                    .price(product.getPrice())
                    .images(product.getImages()).orders(new ArrayList<>()).build());
            modelMap.addAttribute("stateMessage", "Продукт добавлен");
        } else {
            modelMap.addAttribute("stateMessage", "Неверные данные");
        }
        return new ModelAndView(PagesPathEnum.ADMIN_ADD_PRODUCT_PAGE.getPath(), modelMap);
    }
}
