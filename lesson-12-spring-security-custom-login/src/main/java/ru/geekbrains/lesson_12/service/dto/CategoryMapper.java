package ru.geekbrains.lesson_12.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.lesson_12.persist.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto fromCategory(Category category);
}
