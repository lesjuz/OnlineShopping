package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.ImageModel;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.exception.ImageException;
import edu.miu.onlineshopping.service.CategoryServiceImpl;
import edu.miu.onlineshopping.service.OrderItemService;
import edu.miu.onlineshopping.service.ProductServiceImpl;
import edu.miu.onlineshopping.service.UserServiceImpl;
import edu.miu.onlineshopping.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserServiceImpl userService;
    @GetMapping("product/newProduct")
    public String getForm(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        List<Category> category=categoryService.findAll();

        model.addAttribute("cats",category);
        return "productForm";
    }

    @PostMapping("product/newProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult rs, Model model, Authentication authentication) throws IOException {
        if(rs.hasErrors()){
            List<Category> category=categoryService.findAll();

            model.addAttribute("cats",category);
            return "productForm";
        }
        MultipartFile productImage = product.getImageFile();
        if (productImage != null && !productImage.isEmpty()) {
            String extension = Util.getExtension(productImage.getOriginalFilename());
            if(!Util.isItImage(extension) ){

                throw new ImageException("File must be image");

            }

            String imgFileName = java.util.UUID.randomUUID() + extension;
            String dir = Util.getImagesFolder(servletContext);
            productImage.transferTo(new File(dir, imgFileName));
            product.setImage(imgFileName);
        }
        User user=userService.findUserByEmail(authentication.getName());
        product.setSupplier(user);
        productService.save(product);
        return "redirect:/seller/products";
    }

    @GetMapping({"/","/products",""})
    public String dis(Model model,Authentication authentication){
        User user=userService.findUserByEmail(authentication.getName());
        List<Product> p=productService.findByUser(user.getId());
        model.addAttribute("products",p);
        return "productsList";
    }
    @GetMapping("/order/list")
    public String orderList(Model model,@RequestParam(value = "message", required = false) String msg) {

        model.addAttribute("orders", orderItemService.findBySeller(getUser().getId()));
        return "orderList";
    }

    @GetMapping("/order/change/{orderId}/{status}")

    public String changeOrder(@PathVariable("orderId") long orderId,@PathVariable("status") String status)  {
        orderItemService.setStatus(getUser().getId(),orderId,status.toUpperCase());

        return "redirect:/seller/order/list";
    }
    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userService.findUserByEmail(email);
        return user;
    }


    @GetMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable("productId") long productId, Model model)  {
        List<Category> category=categoryService.findAll();
        model.addAttribute("categories", category);
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "editProduct";
    }
    @PostMapping("/product/edit/{productId}")
    public String updateProduct(@PathVariable("productId") long productId, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "editProduct";
        }
        productService.updateProduct(productId, product);

        return "redirect:/seller/products";
    }
    @GetMapping("/product/edit/image/{productId}")
    public String editProductImage(@PathVariable("productId") long productId, Model model) {
        Product product = productService.findById(productId);

        ImageModel productImageModel = new ImageModel(product.getId(), product.getImage());

        model.addAttribute("productImageModel", productImageModel);
        return "editPhoto";
    }
    @PostMapping("/product/edit/image/{productId}")
    public String updateProductImage(@PathVariable("productId") long productId, @ModelAttribute("productImageModel") ImageModel productImageModel, Model model) throws IOException {
       System.out.println("hello");
        MultipartFile image = productImageModel.getImage();
        String imgFileName = "";
        if (image != null && !image.isEmpty()) {
            String extension = Util.getExtension(image.getOriginalFilename());
            if(!Util.isItImage(extension) ){
                throw new ImageException("File must be image");
            }
            System.out.println("hello2");
            imgFileName = java.util.UUID.randomUUID() + extension;
            String dir = Util.getImagesFolder(servletContext);
            image.transferTo(new File(dir, imgFileName));
        }
        productService.updatedProductImage(productId, imgFileName);
        return "redirect:/seller/products";

    }
    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") long productId) {

        productService.deleteProduct(productId);
        return "redirect:/seller/products";
    }


}
