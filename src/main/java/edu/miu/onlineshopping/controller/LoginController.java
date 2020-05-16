package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class LoginController {
     @Autowired
    ProductService productService;
    @RequestMapping({"/"})
    public String root(Authentication authentication) {
       if(authentication.isAuthenticated()){
           if(hasRole("SELLER")){
               return "redirect:/seller/";
           }
           if(hasRole("ADMIN")){
               return "redirect:/admin/";
           }
           if(hasRole("BUYER")){
               return "redirect:/buyer/";
           }
       }
       else{
           return "redirect:/home";
       }
        return "";
    }

    @RequestMapping("/home")
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }


    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        String successMessge=null;
        String userCreated= (String) model.asMap().get("userCreated");
        if (error != null) {
            errorMessge = "Username or Password is incorrect or your account is not active !!";
        }
        if (logout != null) {
            successMessge = "You have been successfully logged out !!";
        }

        if(userCreated!=null){
            successMessge=userCreated;
        }
        model.addAttribute("errorMessge", errorMessge);
        model.addAttribute("successMessge", successMessge);
        return "login2";
    }
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }


}

