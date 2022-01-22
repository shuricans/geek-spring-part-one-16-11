package ru.geekbrains.service;

import ru.geekbrains.persist.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long categoryId);
}
