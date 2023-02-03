package com.example.demo.BookshopSystem.services;

import com.example.demo.BookshopSystem.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
