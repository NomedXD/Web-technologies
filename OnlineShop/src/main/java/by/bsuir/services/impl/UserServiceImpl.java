package by.bsuir.services.impl;

import by.bsuir.domain.User;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.UserRepository;
import by.bsuir.repositories.impl.UserRepositoryImpl;
import by.bsuir.services.UserService;
import by.bsuir.utils.ValidatorUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();
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
        if(ValidatorUtils.validatePasswordMatching(request.getParameter("password"), request.getParameter("repeatPassword"))) {
            try {
                userRepository.create(User.builder()
                        .mail(request.getParameter("mail"))
                        .password(request.getParameter("password"))
                        .name(request.getParameter("name"))
                        .surname(request.getParameter("surname"))
                        .date(LocalDate.parse((CharSequence) request.getParameter("date"))).build());
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (SQLExecutionException e) {
                request.setAttribute("errorMessage", e.getMessage());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
                requestDispatcher.forward(request, response);
            }
        }
    }
}
