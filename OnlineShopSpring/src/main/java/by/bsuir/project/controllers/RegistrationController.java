package by.bsuir.project.controllers;

import by.bsuir.project.domain.User;
import by.bsuir.project.enums.EshopConstants;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({EshopConstants.USER})
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getRegistrationPage() {
        return new ModelAndView(PagesPathEnum.REGISTRATION_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView register(@Valid @ModelAttribute(EshopConstants.USER) User user, BindingResult bindingResult,
                                 @RequestParam(name = "repeatPasswordParam") String repeatPassword) {
        return userService.register(user, bindingResult, repeatPassword);
    }

    @ModelAttribute(EshopConstants.USER)
    public User setUpUser() {
        return new User();
    }
}
