package by.bsuir.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.bsuir.domain.User;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.UserRepository;
import by.bsuir.repositories.impl.UserRepositoryImpl;
import by.bsuir.services.OrderService;
import by.bsuir.services.UserService;
import by.bsuir.utils.ValidatorUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public List<User> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ValidatorUtils.validatePasswordMatching(request.getParameter("password"), request.getParameter("repeatPassword"))) {
            try {
                userRepository.create(User.builder()
                        .mail(request.getParameter("mail"))
                        .password(request.getParameter("password"))
                        .name(request.getParameter("name"))
                        .surname(request.getParameter("surname"))
                        .date(LocalDate.parse(request.getParameter("date"))).build());
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (SQLExecutionException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
            }
        }
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Optional<User> user = userRepository.findUserByMail(request.getParameter("mail"));
            if (user.isPresent() && BCrypt.verifyer().verify(request.getParameter("password").toCharArray(), user.get().getPassword()).verified) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user.get());
                String defaultSuccessUrl = "/shop";
                if (!request.getParameter("defaultSuccessUrl").isEmpty()) {
                    defaultSuccessUrl = request.getParameter("defaultSuccessUrl");
                }
                response.sendRedirect(request.getContextPath() + defaultSuccessUrl);
            } else {
                request.setAttribute("loginErrorMessage", "Wrong email or password. Try again");
                request.getRequestDispatcher(PagesPathEnum.LOG_IN_PAGE.getPath()).forward(request, response);
            }
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/shop");
    }

    @Override
    public void getAccountPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("orders", orderService.getOrdersByUserId(((User) request.getSession().getAttribute("user")).getId()));
            request.getRequestDispatcher(PagesPathEnum.ACCOUNT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    @Override
    public void updateAccountData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> params = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        params.put("mobile", request.getParameter("mobile"));
        params.put("street", request.getParameter("street"));
        params.put("accommodationNumber", request.getParameter("accommodationNumber"));
        params.put("flatNumber", request.getParameter("flatNumber"));
        setInputs(params, user);
        User updatedUserFields = User.builder().id(user.getId()).mail(user.getMail()).password(user.getPassword()).
                name(user.getName()).surname(user.getSurname()).date(user.getDate()).
                mobile(params.get("mobile")).street(params.get("street")).
                accommodationNumber(params.get("accommodationNumber")).
                flatNumber(params.get("flatNumber")).build();
        try {
            request.getSession().setAttribute("user", userRepository.update(updatedUserFields));
            request.setAttribute("orders", orderService.getOrdersByUserId(((User) request.getSession().getAttribute("user")).getId()));
            request.getRequestDispatcher(PagesPathEnum.ACCOUNT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
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
}
