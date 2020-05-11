package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.repository.CategoryRepository;
import edu.miu.onlineshopping.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void save(Category category) {
        System.out.println(category.getName());
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return  Util.iterableToCollection(categoryRepository.findAll());
    }
}
