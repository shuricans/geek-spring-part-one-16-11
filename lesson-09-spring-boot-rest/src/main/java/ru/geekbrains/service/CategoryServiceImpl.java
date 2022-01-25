package ru.geekbrains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
