package by.bsuir.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cart {
    private final Map<Integer, Product> products;

    private float totalPrice;

    private DiscountCode appliedDiscountCode;

    public Cart() {
        this.products = new HashMap<>();
        this.totalPrice = 0;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        totalPrice += product.getPrice() * 1;
    }

    public void removeProduct(int productId) {
        Product product = products.get(productId);
        products.remove(productId);
        totalPrice -= product.getPrice();
    }

    public static Cart checkCart(Product product, Cart cart) {
        if (cart != null) {
            cart.addProduct(product);
        } else {
            cart = new Cart();
            cart.addProduct(product);
        }
        return cart;
    }

    public static Cart applyDiscountCode(DiscountCode discountCode, Cart cart) {
        if (cart != null) {
            cart.setAppliedDiscountCode(discountCode);
        } else {
            cart = new Cart();
            cart.setAppliedDiscountCode(discountCode);
        }
        return cart;
    }

    public List<Product> getCartProductsInList() {
        return new ArrayList<>(products.values());
    }

    public int getTotalSize() {
        return products.size();
    }

    public void clear() {
        products.clear();
        totalPrice = 0;
        appliedDiscountCode = null;
    }

}
