package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Category;

import java.util.List;

public interface CategoryService {
    public void save(Category category);
    public List<Category > findAll();
}
