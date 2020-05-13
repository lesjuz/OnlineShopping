package edu.miu.onlineshopping.controller;

import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.service.CategoryServiceImpl;
import edu.miu.onlineshopping.service.ProductServiceImpl;
import edu.miu.onlineshopping.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.FileSystemNotFoundException;
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
    UserServiceImpl userService;
    @GetMapping("product/newProduct")
    public String getForm(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        List<Category> category=categoryService.findAll();

        model.addAttribute("cats",category);
        return "productForm";
    }
    @GetMapping("product/edit/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Product product=productService.findById(id).get();
        model.addAttribute("product",product);
        List<Category> category=categoryService.findAll();

        model.addAttribute("cats",category);
        return "viewProduct";
    }
    @GetMapping("product/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        System.out.println("DELETED "+id);
        productService.deleteById(id);
        return "redirect:/seller/";
    }
    @PostMapping("product/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product, Model model){
        productService.save(product);
        return "redirect:/seller/";
    }
    @PostMapping("product/newProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult rs, Model model, Authentication authentication){
        if(rs.hasErrors()){
            List<Category> category=categoryService.findAll();

            model.addAttribute("cats",category);
            return "productForm";
        }
        MultipartFile image = product.getImageFile();
        String rootDirectory = servletContext.getRealPath("/");
        System.out.println("######### root directory "+rootDirectory);

        //isEmpty means file exists BUT NO Content
       /* if (image!=null && !image.isEmpty()) {
            try {
                image.transferTo(new File(rootDirectory+"\\images\\"+ product.getName() + ".jpg"));
            } catch (Exception e) {
                System.out.println("########### "+e.getMessage());
                throw new FileSystemNotFoundException("Unable to save image: " + image.getOriginalFilename() );
            }
        }*/
        User user=userService.findUserByEmail(authentication.getName());
        product.setSupplier(user);
        productService.save(product);
        return "redirect:/seller/";
    }

    @GetMapping({"/","/product"})
    public String dis(Model model,Authentication authentication){
        User user=userService.findUserByEmail(authentication.getName());
        List<Product> p=productService.findByUser(user.getId());
        model.addAttribute("products",p);
        return "productsList";
    }
}
