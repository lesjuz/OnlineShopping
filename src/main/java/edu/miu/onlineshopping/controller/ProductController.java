package edu.miu.onlineshopping.controller;


import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.service.CategoryServiceImpl;
import edu.miu.onlineshopping.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping({"/product"})
public class ProductController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;
    @GetMapping("/newProduct")
    public String getForm(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        List<Category> category=categoryService.findAll();

        model.addAttribute("cats",category);
        return "productForm";
    }
    @GetMapping("/edit/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Product product=productService.find(id);
        model.addAttribute("product",product);
        List<Category> category=categoryService.findAll();

        model.addAttribute("cats",category);
        return "viewProduct";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        System.out.println("DELETED "+id);
        Product p=productService.find(id);

        try {
            productService.delete(p);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/product/";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product, Model model){
        productService.save(product);
        return "redirect:/product/";
    }
    @PostMapping("/newProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult rs, Model model){
        if(rs.hasErrors()){
            List<Category> category=categoryService.findAll();

            model.addAttribute("cats",category);
            return "productForm";
        }
        MultipartFile image = product.getImageFile();
        String rootDirectory = servletContext.getRealPath("/");
        System.out.println("######### root directory "+rootDirectory);

        //isEmpty means file exists BUT NO Content
        if (image!=null && !image.isEmpty()) {
            try {
                image.transferTo(new File(rootDirectory+"\\image\\"+ product.getName() + ".png"));
            } catch (Exception e) {
                System.out.println("########### "+e.getMessage());
                throw new FileSystemNotFoundException("Unable to save image: " + image.getOriginalFilename() );
            }
        }
        productService.save(product);
        return "redirect:/product/";
    }

    @GetMapping({"/"})
    public String dis(Model model){
        List<Product> p=productService.findAll();
        model.addAttribute("products",p);
        return "productsList";
    }
}
