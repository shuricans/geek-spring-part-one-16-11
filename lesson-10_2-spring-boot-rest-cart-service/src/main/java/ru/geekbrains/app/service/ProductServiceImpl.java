package ru.geekbrains.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.app.persist.Product;
import ru.geekbrains.app.persist.Category;
import ru.geekbrains.app.persist.ProductRepository;
import ru.geekbrains.app.persist.ProductSpecification;
import ru.geekbrains.app.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

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
                .map(ProductServiceImpl::convertProductToProductDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convertProductToProductDto);
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
        Category category = categoryService.findById(productDto.getCategoryId())
                .map(catDto -> new Category(catDto.getId(), catDto.getName(), null))
                .orElse(null);

        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                category);
        return convertProductToProductDto(productRepository.save(product));
    }

    private static ProductDto convertProductToProductDto(Product p) {
        return new ProductDto(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null);
    }


    private <T> Specification<T> combineSpec(Specification<T> s1, Specification<T> s2) {
        return s1 == null ? Specification.where(s2) : s1.and(s2);
    }
}
