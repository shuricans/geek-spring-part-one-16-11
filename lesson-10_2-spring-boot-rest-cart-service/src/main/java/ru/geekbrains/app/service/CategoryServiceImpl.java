package ru.geekbrains.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.app.persist.CategorySpecification;
import ru.geekbrains.app.persist.Category;
import ru.geekbrains.app.persist.CategoryRepository;
import ru.geekbrains.app.service.dto.CategoryDto;

import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> findAll(Optional<String> nameFilter,
                                     Integer page,
                                     Integer size,
                                     String sortField,
                                     Sort.Direction direction) {

        Specification<Category> spec = null;

        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = Specification.where(
                    CategorySpecification.nameLike(nameFilter.get().toLowerCase(Locale.ROOT)));
        }

        spec = combineSpec(spec, Specification.where(null));

        return categoryRepository.findAll(spec, PageRequest.of(page, size, Sort.by(direction, sortField)))
                .map(CategoryServiceImpl::convertCategoryToCategoryDto);
    }

    @Override
    public Optional<CategoryDto> findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryServiceImpl::convertCategoryToCategoryDto);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getId(),
                categoryDto.getName(), null);

        return convertCategoryToCategoryDto(categoryRepository.save(category));
    }

    private static CategoryDto convertCategoryToCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    private <T> Specification<T> combineSpec(Specification<T> s1, Specification<T> s2) {
        return s1 == null ? Specification.where(s2) : s1.and(s2);
    }
}
