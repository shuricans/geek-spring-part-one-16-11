package ru.geekbrains.lesson_12.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.lesson_12.persist.Product;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);

}
