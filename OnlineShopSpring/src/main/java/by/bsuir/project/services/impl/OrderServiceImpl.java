package by.bsuir.project.services.impl;

import by.bsuir.project.domain.Cart;
import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.OrderDetails;
import by.bsuir.project.domain.User;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.repositories.OrderRepository;
import by.bsuir.project.services.OrderService;
import by.bsuir.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Order create(Order entity) {
        return orderRepository.create(entity);
    }

    @Override
    public List<Order> read() {
        return orderRepository.read();
    }

    @Override
    public Order update(Order entity) {
        return orderRepository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    @Override
    public List<Order> getPaginatedOrders(Integer currentPage, Integer pageSize, Integer userId) {
        return orderRepository.findAllByUserId(userId, currentPage, pageSize);
    }

    @Override
    public Long getCountUserOrders(Integer userId) {
        return orderRepository.countAllByUserId(userId);
    }


    @Override
    public ModelAndView applyOrder(Order order, Cart cart, User user) {
        preBuildOrder(order, cart, user);
        user.getOrders().add(orderRepository.create(order));
        user = userService.update(user);
        cart.clear();
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath());
    }

    private void preBuildOrder(Order order, Cart cart, User user) {
        order.setDate(LocalDate.now());
        order.setPrice(cart.getTotalPrice());
        String ccNumber = order.getCreditCardNumber();
        order.setCreditCardNumber(ccNumber.substring(0, 5).concat(" **** **** ").concat(ccNumber.substring(ccNumber.length()-5)));
        order.setUser(user);
        order.setProductList(cart.getCartProductsInList());
        order.setDiscountCode(cart.getAppliedDiscountCode());
        if(Optional.ofNullable(order.getOrderDetails()).isEmpty()){
            order.setOrderDetails(new ArrayList<>());
        }
        cart.getProductQuantities().forEach((productId, productQuantity)-> order.getOrderDetails().add(OrderDetails.builder().order(order).productId(productId).productQuantity(productQuantity).build()));
    }
}
