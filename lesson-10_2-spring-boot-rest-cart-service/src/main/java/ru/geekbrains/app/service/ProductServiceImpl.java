package ru.geekbrains.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.app.persist.Product;
import ru.geekbrains.app.persist.ProductRepository;
import ru.geekbrains.app.persist.ProductSpecification;
import ru.geekbrains.app.service.dto.ProductDto;
import ru.geekbrains.app.service.dto.ProductMapper;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> findAll(
            Optional<String> nameFilter,
            Optional<BigDecimal> minPrice,
            Optional<BigDecimal> maxPrice,
            Integer page,
            Integer size,
            String sortField,
            Sort.Direction direction) {
        Specification<Product> spec = null;

        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = Specification.where(
                    ProductSpecification.nameLike(nameFilter.get().toLowerCase(Locale.ROOT)));
        }

        if (minPrice.isPresent()) {
            spec = combineSpec(spec, ProductSpecification.minPriceFilter(minPrice.get()));
        }

        if (maxPrice.isPresent()) {
            spec = combineSpec(spec, ProductSpecification.maxPriceFilter(maxPrice.get()));
        }

        spec = combineSpec(spec, Specification.where(null));

        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(direction, sortField)))
                .map(productMapper::fromProduct);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::fromProduct);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        product = productRepository.save(product);
        return productMapper.fromProduct(product);
    }

    private <T> Specification<T> combineSpec(Specification<T> s1, Specification<T> s2) {
        return s1 == null ? Specification.where(s2) : s1.and(s2);
    }
}
