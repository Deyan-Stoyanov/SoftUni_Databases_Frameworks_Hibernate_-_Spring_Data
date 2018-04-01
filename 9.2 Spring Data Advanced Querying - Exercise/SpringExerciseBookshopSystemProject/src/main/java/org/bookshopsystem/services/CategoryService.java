package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Category;

import java.util.List;

public interface CategoryService {
    void addCategoryToDb(Category category);
    List<Category> getAll();
}
