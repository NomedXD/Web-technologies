package by.bsuir.project.services;

import by.bsuir.project.domain.Cart;
import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    List<Order> getPaginatedOrders(Integer currentPage, Integer pageSize, Integer userId);
    Long getCountUserOrders(Integer userId);
    ModelAndView applyOrder(Order order, Cart cart, User user);
}
