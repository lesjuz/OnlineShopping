package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.OrderDetail;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.service.OrderDetailService;
import edu.miu.onlineshopping.service.OrderItemService;
import edu.miu.onlineshopping.service.ProductService;
import edu.miu.onlineshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserService userService;



    @RequestMapping({"/products","/",""})
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "allProducts";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        Product product=productService.findById(productId);

        model.addAttribute("product",product);
        return "product";
    }
    @GetMapping("/order/list")
    public String orderList(Model model,@RequestParam(value = "message", required = false) String msg) {

        model.addAttribute("orders", orderDetailService.getMyOrders(getBuyer()));
        return "orderHistory";
    }

    @GetMapping("/order/cancel/{orderId}")

    public String cancelOrder(@PathVariable("orderId") long orderId, RedirectAttributes redirectAttributes)  {
        orderItemService.cancelOrder(getBuyer().getId(), orderId);
        redirectAttributes.addAttribute("msg","canceled");
        return "redirect:/buyer/order/list";
    }

    public User getBuyer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User buyer=userService.findUserByEmail(email);
        return buyer;
    }


}
