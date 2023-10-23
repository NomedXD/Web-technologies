package by.bsuir.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        if (ValidatorUtils.validatePasswordMatching(request.getParameter("password"), request.getParameter("repeatPassword"))) {
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

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Optional<User> user = userRepository.findUserByMail(request.getParameter("mail"));
            if (user.isPresent() && BCrypt.verifyer().verify(request.getParameter("password").toCharArray(), user.get().getPassword()).verified) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user.get());
                response.sendRedirect(request.getContextPath() + "/shop");
            } else {
                request.setAttribute("loginErrorMessage", "Wrong email or password. Try again");
                request.getRequestDispatcher(PagesPathEnum.LOG_IN_PAGE.getPath()).forward(request, response);
            }
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/shop");
    }
}
