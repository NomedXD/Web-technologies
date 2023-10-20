package by.bsuir.services;

import by.bsuir.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService extends BaseService<User>{
    void register(HttpServletRequest request, HttpServletResponse response);
}
