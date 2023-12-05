package by.bsuir.project.services.impl;

import by.bsuir.project.principal.UserPrincipal;
import by.bsuir.project.domain.Role;
import by.bsuir.project.domain.User;
import by.bsuir.project.enums.EshopConstants;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.enums.RequestParamsEnum;
import by.bsuir.project.enums.UserRoleEnum;
import by.bsuir.project.repositories.UserRepository;
import by.bsuir.project.services.OrderService;
import by.bsuir.project.services.UserService;
import by.bsuir.project.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy OrderService orderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
    }

    @Override
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.create(entity);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByMail(String userMail) {
        return userRepository.findUserByMail(userMail);
    }

    @Override
    public ModelAndView getAccount(Integer userId, Integer currentPage, Integer pageSize) {
        if (Optional.ofNullable(currentPage).isEmpty() || Optional.ofNullable(pageSize).isEmpty()) {
            currentPage = 1;
            pageSize = EshopConstants.MIN_PAGE_SIZE;
        }
        ModelMap model = new ModelMap();
        model.addAttribute(RequestParamsEnum.CURRENT_PAGE.getValue(), currentPage);
        model.addAttribute(RequestParamsEnum.PAGE_SIZE.getValue(), pageSize);
        model.addAttribute(RequestParamsEnum.TOTAL_PAGINATED_VISIBLE_PAGES.getValue(), EshopConstants.TOTAL_PAGINATED_VISIBLE_PAGES);
        model.addAttribute(RequestParamsEnum.LAST_PAGE_NUMBER.getValue(), Math.ceil(orderService.getCountUserOrders(userId) / pageSize.doubleValue()));
        model.addAttribute(RequestParamsEnum.ORDERS.getValue(), orderService.getPaginatedOrders(currentPage, pageSize, userId));

        return new ModelAndView(PagesPathEnum.ACCOUNT_PAGE.getPath(), model);
    }

    @Override
    public ModelAndView updateAccountData(User updatedUserFields, User user, Integer currentPage, Integer pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put(RequestParamsEnum.MOBILE.getValue(), updatedUserFields.getMobile());
        params.put(RequestParamsEnum.STREET.getValue(), updatedUserFields.getStreet());
        params.put(RequestParamsEnum.ACCOMMODATION_NUMBER.getValue(), updatedUserFields.getAccommodationNumber());
        params.put(RequestParamsEnum.FLAT_NUMBER.getValue(), updatedUserFields.getFlatNumber());
        setInputs(params, user);
        updatedUserFields = User.builder().id(user.getId()).mail(user.getMail()).password(user.getPassword()).
                name(user.getName()).surname(user.getSurname()).date(user.getDate()).
                mobile(params.get(RequestParamsEnum.MOBILE.getValue())).street(params.get(RequestParamsEnum.STREET.getValue())).
                accommodationNumber(params.get(RequestParamsEnum.ACCOMMODATION_NUMBER.getValue())).
                flatNumber(params.get(RequestParamsEnum.FLAT_NUMBER.getValue())).roles(user.getRoles()).build();
        ModelMap model = new ModelMap();
        updatedUserFields.setOrders(userRepository.findOrdersByUserId(user.getId()));
        ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setUser(update(updatedUserFields));
        model.addAttribute(RequestParamsEnum.CURRENT_PAGE.getValue(), currentPage);
        model.addAttribute(RequestParamsEnum.PAGE_SIZE.getValue(), pageSize);
        model.addAttribute(RequestParamsEnum.TOTAL_PAGINATED_VISIBLE_PAGES.getValue(), EshopConstants.TOTAL_PAGINATED_VISIBLE_PAGES);
        model.addAttribute(RequestParamsEnum.LAST_PAGE_NUMBER.getValue(), Math.ceil(orderService.getCountUserOrders(user.getId()) / pageSize.doubleValue()));
        model.addAttribute(EshopConstants.ORDERS, orderService.getPaginatedOrders(currentPage, pageSize, user.getId()));
        return new ModelAndView(PagesPathEnum.ACCOUNT_PAGE.getPath(), model);
    }

    private void setInputs(Map<String, String> params, User user) {
        for (var entry : params.entrySet()) {
            if (entry.getValue().isEmpty()) {
                switch (entry.getKey()) {
                    case "mobile" -> entry.setValue(user.getMobile());
                    case "street" -> entry.setValue(user.getStreet());
                    case "accommodationNumber" -> entry.setValue(user.getAccommodationNumber());
                    case "flatNumber" -> entry.setValue(user.getFlatNumber());
                }
            }
        }
    }

    @Override
    public ModelAndView logIn(String error) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.LOG_IN_PAGE.getPath());
        if (error != null) {
            modelAndView.addObject("loginErrorMessage", "Wrong email or password. Try again");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView register(User user, BindingResult bindingResult, String repeatPassword) {
        if (!bindingResult.hasErrors() && ValidatorUtils.validatePasswordMatching(user.getPassword(), repeatPassword)) {
            userRepository.create(User.builder().mail(user.getMail()).password(passwordEncoder.encode(user.getPassword())).name(user.getName()).
                    surname(user.getSurname()).date(user.getDate()).orders(new ArrayList<>()).roles(List.of(Role.builder().id(2).name(UserRoleEnum.USER.name()).build())).build());
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("loginErrorMessage", "Now you can log in");
            return new ModelAndView("redirect:/login", modelMap);
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("registrationErrorMessage", "Wrong credentials matching");
        return new ModelAndView(PagesPathEnum.REGISTRATION_PAGE.getPath(), modelMap);
    }
}
