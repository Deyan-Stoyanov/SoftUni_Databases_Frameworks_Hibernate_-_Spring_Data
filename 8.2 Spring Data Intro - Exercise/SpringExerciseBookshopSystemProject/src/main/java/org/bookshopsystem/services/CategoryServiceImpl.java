package org.bookshopsystem.services;

import org.bookshopsystem.models.entities.Category;
import org.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategoryToDb(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> getAll() {
       return this.categoryRepository.findAll();
    }
}
