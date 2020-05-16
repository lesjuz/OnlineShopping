package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.domain.UserModel;
import edu.miu.onlineshopping.repository.UserRepository;
import edu.miu.onlineshopping.service.ProductService;
import edu.miu.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {


    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;

    private UserModel userModel = null;
    private User user = null;


    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        if(session.getAttribute("userModel")==null) {
            // get the authentication object
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if(!authentication.getPrincipal().equals("anonymousUser")){
                // get the user from the database
                user = userService.findUserByEmail(authentication.getName());
                String role = user.getRoles().stream().findFirst().get().getRole();

                if(user!=null) {
                    // create a new model
                    userModel = new UserModel();
                    // set the name and the id
                    userModel.setId(user.getId());
                    userModel.setFullName(user.getName() + " " + user.getLastName());
                    userModel.setRole(role);

                    if(role.equals("BUYER")) {
                        userModel.setCart(user.getCart());
                    }

                    session.setAttribute("userModel", userModel);
                    return userModel;
                }
            }
        }

        return (UserModel) session.getAttribute("userModel");
    }
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        Product product=productService.findById(productId);

        model.addAttribute("product",product);
        return "product";
    }
}
