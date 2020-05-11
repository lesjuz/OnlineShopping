package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private  UserService userService;
    @GetMapping(value = "/")
    public String getUsers(User user, Model model){
        List<User> users=new ArrayList<>();
        users=userService.getAll();

        model.addAttribute("users",users);

        return "user/admin";

    }

    @GetMapping(value = "/users/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String changeUserStatus(@RequestParam(value = "email", required = false) String email){
        User updatedUser=userService.findUserByEmail(email);
        System.out.println(updatedUser);
        if(updatedUser!=null){
        if(updatedUser.getActive()==1){

            updatedUser.setActive(0);
        }
        else{
            updatedUser.setActive(1);
        }
            userService.updateUser(updatedUser);

        }
        return "redirect:/admin/";    }
}
