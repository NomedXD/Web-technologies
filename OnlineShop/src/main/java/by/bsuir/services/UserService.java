package by.bsuir.services;

import by.bsuir.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserService extends BaseService<User>{
    void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void getAccountPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void updateAccountData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
