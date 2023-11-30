package by.bsuir.project.services;

import by.bsuir.project.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface UserService extends BaseService<User> {
    Optional<User> getUserById(Integer id);

    Optional<User> getUserByMail(String userMail);

    ModelAndView getAccount(Integer userId, Integer currentPage, Integer pageSize);

    ModelAndView updateAccountData(User updatedUserFields, User user, Integer currentPage, Integer pageSize);

    ModelAndView logIn(String error);

    ModelAndView register(User user, BindingResult bindingResult, String repeatPassword);
}
