package edu.miu.onlineshopping.controller;


import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.service.RoleService;
import edu.miu.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> getRoles(Model model) {
        return roleService.findAll();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute("user") User user) {

        return "signup";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes rd) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult
                    .rejectValue("confirmPassword", "error.user",
                            "Password does not match!");
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        } else {

            userService.saveUser(user);
            rd.addFlashAttribute("userCreated","User successfully created!");
            return "redirect:/login";

        }



    }
}
