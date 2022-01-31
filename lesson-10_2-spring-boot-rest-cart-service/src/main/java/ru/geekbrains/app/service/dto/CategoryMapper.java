package ru.geekbrains.app.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.app.persist.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto fromCategory(Category category);
}
