package ru.geekbrains.service;

import ru.geekbrains.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll(
            Optional<String> nameFilter,
            Optional<BigDecimal> minPrice,
            Optional<BigDecimal> maxPrice);

    Optional<ProductDto> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    ProductDto save(ProductDto product);
}
