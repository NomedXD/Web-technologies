package by.bsuir.project.controllers.admin_controllers;

import by.bsuir.project.enums.PagesPathEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {
    @GetMapping
    public ModelAndView getAccountPage() {
        return new ModelAndView(PagesPathEnum.ADMIN_ACCOUNT_PAGE.getPath());
    }
}
