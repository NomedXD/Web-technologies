package by.bsuir.project.controllers;

import by.bsuir.project.domain.Cart;
import by.bsuir.project.domain.DiscountCode;
import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.User;
import by.bsuir.project.enums.EshopConstants;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.enums.RequestParamsEnum;
import by.bsuir.project.exception.NoSuchProductException;
import by.bsuir.project.services.DiscountCodeService;
import by.bsuir.project.services.OrderService;
import by.bsuir.project.services.ProductService;
import by.bsuir.project.utils.SecurityContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes(EshopConstants.SHOPPING_CART)
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;
    private final OrderService orderService;
    private final DiscountCodeService discountCodeService;

    public CartController(ProductService productService, OrderService orderService, DiscountCodeService discountCodeService) {
        this.productService = productService;
        this.orderService = orderService;
        this.discountCodeService = discountCodeService;
    }

    @GetMapping
    public ModelAndView getShoppingCart() {
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath());
    }

    @GetMapping("/remove/{productId}")
    public ModelAndView deleteProductFromCart(@PathVariable(name = "productId") Integer productId,
                                              @SessionAttribute(EshopConstants.SHOPPING_CART) Cart cart) {
        ModelMap model = new ModelMap();
        cart.removeProduct(productId);
        model.addAttribute(RequestParamsEnum.SHOPPING_CART_PRODUCTS.getValue(), cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), model);
    }

    @GetMapping("/add/{productId}")
    public ModelAndView addProductToCart(@PathVariable(name = "productId") Integer productId,
                                         @SessionAttribute(name = EshopConstants.SHOPPING_CART, required = false) Cart cart) {
        ModelMap model = new ModelMap();
        Product product = productService.getProductById(productId).orElseThrow(() -> new NoSuchProductException("Product not found. How you even get there?", productId));
        model.addAttribute(EshopConstants.SHOPPING_CART, Cart.checkCart(product, cart));
        model.addAttribute(RequestParamsEnum.PRODUCT.getValue(), product);
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), model);
    }

    @PostMapping("/checkout")
    public ModelAndView submitCheckout(@ModelAttribute(name = EshopConstants.ORDER) Order order,
                                       @SessionAttribute(name = EshopConstants.SHOPPING_CART) Cart cart) {
        User user = SecurityContextUtils.getUser().orElseThrow(SecurityException::new);
        return orderService.applyOrder(order, cart, user);
    }

    @PostMapping("/apply_quantity")
    public ModelAndView applyQuantity(@SessionAttribute(name = EshopConstants.SHOPPING_CART) Cart cart, HttpServletRequest request) {
        return productService.applyProductsQuantity(cart, request);
    }

    @PostMapping("/check_code")
    public ModelAndView checkCode(@SessionAttribute(name = EshopConstants.SHOPPING_CART, required = false) Cart cart, @RequestParam(name = "discountCode") String discountCode) {
        ModelMap model = new ModelMap();
        Optional<DiscountCode> code = discountCodeService.getDiscountCodeByName(discountCode);
        if (code.isPresent()) {
            model.addAttribute(EshopConstants.SHOPPING_CART, Cart.applyDiscountCode(code.get(), cart));
        } else {
            model.addAttribute(RequestParamsEnum.DISCOUNT_CODE_MESSAGE.getValue(), EshopConstants.errorDiscountCodeMessage);
        }
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), model);
    }

    @ModelAttribute(EshopConstants.ORDER)
    public Order setUpOrder() {
        return new Order();
    }
}
