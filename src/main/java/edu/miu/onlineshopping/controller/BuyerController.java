package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private ProductService productService;

    @RequestMapping({"/products","/"})
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "allProducts";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        Product product=productService.findById(productId).get();

        model.addAttribute("product",product);
        return "product";
    }


}
