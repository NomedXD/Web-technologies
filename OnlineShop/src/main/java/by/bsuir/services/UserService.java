package by.bsuir.services;

import by.bsuir.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserService extends BaseService<User> {
    /**
     * Performs password and mail validation and user registration
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Retrieves the user from the database by mail. Validates the password
     * <p>
     * Depending on the validation result and the user's role, a specific page is returned
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Logs out of the user's account
     * <p>
     * Clears the user's session
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException if occurs dispatch error
     */
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Returns the account page depending on the user's role
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void getAccountPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Updates the user's account data
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void updateAccountData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
