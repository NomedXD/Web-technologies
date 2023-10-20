package by.bsuir.services.impl;

import by.bsuir.domain.User;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.services.UserService;
import by.bsuir.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserServiceImpl implements UserService {
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
    public void register(HttpServletRequest request, HttpServletResponse response) {
        if(ValidatorUtils.validatePasswordMatching((String) request.getAttribute("password"), (String) request.getAttribute("repeatPassword"))) {

        }
    }
}
