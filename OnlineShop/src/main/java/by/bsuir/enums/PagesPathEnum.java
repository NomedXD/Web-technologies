package by.bsuir.enums;

import lombok.Getter;

@Getter
public enum PagesPathEnum {
    SHOP_PAGE("/shop.jsp"),
    LOG_IN_PAGE("/login.jsp"),
    REGISTRATION_PAGE("/register.jsp"),
    CATEGORY_PAGE("/category.jsp"),
    CART_PAGE("/cart.jsp"),
    PRODUCT_PAGE("/product.jsp"),
    ACCOUNT_PAGE("/account.jsp"),
    SEARCH_PAGE("/search.jsp"),
    ERROR_PAGE("/error.jsp"),
    ADMIN_ACCOUNT_PAGE("/admin_account.jsp"),
    ADMIN_ADD_CATEGORY_PAGE("/admin_add_category.jsp"),
    ADMIN_ADD_PRODUCT_PAGE("/admin_add_product.jsp");
    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

}

