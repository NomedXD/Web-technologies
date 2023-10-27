package by.bsuir.services.impl;

import by.bsuir.domain.Cart;
import by.bsuir.domain.Order;
import by.bsuir.domain.User;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.OrderRepository;
import by.bsuir.repositories.impl.OrderRepositoryImpl;
import by.bsuir.services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public void create(Order entity) {
    }

    @Override
    public List<Order> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) throws SQLExecutionException {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void applyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            orderRepository.create(preBuildOrder(request));
            ((Cart) request.getSession().getAttribute("cart")).clear();
            request.getRequestDispatcher(PagesPathEnum.CART_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    private Order preBuildOrder(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        Order order = Order.builder().price(cart.getTotalPrice())
                .date(LocalDate.now())
                .userId(user.getId())
                .productList(cart.getCartProductsInList())
                .shippingType(request.getParameter("shippingType"))
                .shippingCost(Float.valueOf(request.getParameter("shippingCost")))
                .discountCode(cart.getAppliedDiscountCode())
                .address(request.getParameter("address"))
                .customerNotes(request.getParameter("customerNotes")).build();
        String ccNumber = request.getParameter("creditCardNumber");
        order.setCreditCardNumber(ccNumber.substring(0, 5).concat(" **** **** ").concat(ccNumber.substring(ccNumber.length() - 5)));
        return order;
    }

    @Override
    public void removeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            orderRepository.delete(Integer.parseInt(request.getParameter("orderId")));
            request.setAttribute("orders", orderRepository.findAllByUserId(((User) request.getSession().getAttribute("user")).getId()));
            request.getRequestDispatcher(PagesPathEnum.ACCOUNT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
