package edu.miu.onlineshopping.controller;


import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.service.AddressService;
import edu.miu.onlineshopping.service.CartService;
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

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

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
            String role = user.getRoles().stream().findFirst().get().getRole();
            user.setActive(1);
            if(role.equals("SELLER")){
                user.setActive(0);
            }
            if(role.equals("BUYER")){

                // create a new cart
                Cart cart = new Cart();
                user.setCart(cart);
                cart.setUser(user);
                cartService.saveCart(cart);

            }
            Address address =user.getAddress();
            User newUser=userService.saveUser(user);
            address.setUserId(newUser.getId());
            if(role.equals("BUYER")){
                address.setBilling(true);
            }
            addressService.saveAddress(address);
            rd.addFlashAttribute("userCreated","User successfully created!");
            return "redirect:/login";

        }



    }
}
