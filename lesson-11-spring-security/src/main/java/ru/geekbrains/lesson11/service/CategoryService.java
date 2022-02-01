package ru.geekbrains.lesson11.service;


import ru.geekbrains.lesson11.persist.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long categoryId);
}
