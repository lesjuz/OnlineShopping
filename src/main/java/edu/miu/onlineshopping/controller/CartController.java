package edu.miu.onlineshopping.controller;


import edu.miu.onlineshopping.domain.*;
import edu.miu.onlineshopping.service.CartItemService;
import edu.miu.onlineshopping.service.CartService;
import edu.miu.onlineshopping.service.CheckoutService;
import edu.miu.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "buyer/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CheckoutService checkoutService;

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

        ModelAndView mv = new ModelAndView("cart");
        mv.addObject("title", "Shopping Cart");
        mv.addObject("userClickShowCart", true);

        if(result!=null) {
            switch(result) {
                case "added":
                    mv.addObject("message", "Product has been successfully added inside cart!");
                    cartService.validateCartItem();
                    break;
                case "unavailable":
                    mv.addObject("message", "Product quantity is not available!");
                    break;
                case "updated":
                    mv.addObject("message", "Cart has been updated successfully!");
                    cartService.validateCartItem();
                    break;
                case "modified":
                    mv.addObject("message", "One or more items inside cart has been modified!");
                    break;
                case "maximum":
                    mv.addObject("message", "Maximum limit for the item has been reached!");
                    break;
                case "deleted":
                    mv.addObject("message", "CartLine has been successfully removed!");
                    break;

            }
        }
        else {
            String response = cartService.validateCartItem();
            if(response.equals("modified")) {
                mv.addObject("message", "One or more items inside cart has been modified!");
            }
        }

        mv.addObject("cartLines", cartService.getCartItems());
        return mv;

    }


    @RequestMapping("/{cartLineId}/update")
    public String udpateCartLine(@PathVariable long cartLineId, @RequestParam int count,RedirectAttributes attributes) {
        String response = cartService.manageCartItem(cartLineId, count);
        attributes.addAttribute("result",response);
        return "redirect:/buyer/cart/show";
    }

    @RequestMapping("/add/{productId}/product")
    public String addCartLine(@PathVariable long productId, RedirectAttributes attributes) {
        String response = cartService.addCartItem(productId);
        System.out.println(response);
        attributes.addAttribute("result",response);
        return "redirect:/buyer/cart/show";
    }

    @RequestMapping("/{cartLineId}/remove")
    public String removeCartLine(@PathVariable int cartLineId,RedirectAttributes attributes) {
        String response = cartService.removeCartItem(cartLineId);
        attributes.addAttribute("result",response);
        return "redirect:/buyer/cart/show";
    }

    /* after validating it redirect to checkout
     * if result received is success proceed to checkout
     * else display the message to the user about the changes in cart page
     * */
    @RequestMapping("/validate")
    public String validateCart(RedirectAttributes attributes) {
        String response = cartService.validateCartItem();
        if(!response.equals("success")) {
            attributes.addAttribute("result",response);
            return "redirect:buyer/cart/show";
        }
        else {
            return "redirect:/buyer/cart/checkout";
        }
    }


    @GetMapping("/checkout")
    public String saveShippingAddress(@ModelAttribute("address")Address address,Model model){


        return "checkout";
    }
    @PostMapping("/checkout")
    public String saveShipping(@Valid Address address, BindingResult bindingResult,Authentication authentication, Model model,HttpSession session) throws Exception {
        String name= authentication.getName();
        if (bindingResult.hasErrors()) {
            return "checkout";
        }
        CheckoutModel checkoutModel=checkoutService.init(name);
        checkoutService.saveAddress(checkoutModel,address);
        session.setAttribute("shippingAddress",address);
        return "redirect:/buyer/cart/checkout/pay";
    }

    @RequestMapping("/checkout/pay")
    public String checkout(Authentication authentication,Model model){
        String name= authentication.getName();

        try {

            model.addAttribute("checkoutModel",checkoutService.init(name));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "checkout2";
    }

    @RequestMapping("/order/confirm")
    public String confirmOrder(Authentication authentication,Model model,HttpSession session){
        String name= authentication.getName();

        try {
            Address address= (Address) session.getAttribute("shippingAddress");
            CheckoutModel cm=checkoutService.saveOrder(checkoutService.init(name),address);

            model.addAttribute("orderDetail",cm.getOrderDetail());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "orderReceipt";
    }
}


