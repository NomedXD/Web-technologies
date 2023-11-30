package by.bsuir.project.controllers;

import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.enums.RequestParamsEnum;
import by.bsuir.project.exception.NoSuchProductException;
import by.bsuir.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ModelAndView getProductPage(@PathVariable("productId") Integer productId) {
        ModelMap model = new ModelMap();
        model.addAttribute(RequestParamsEnum.PRODUCT.getValue(), productService.getProductById(productId).orElseThrow(() -> new NoSuchProductException("Product not found. How you even get there?", productId)));
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), model);
    }
}
