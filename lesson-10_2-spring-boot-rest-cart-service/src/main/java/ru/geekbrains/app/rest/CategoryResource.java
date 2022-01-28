package ru.geekbrains.app.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.app.service.CategoryService;
import ru.geekbrains.app.service.dto.CategoryDto;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryDto> search(@RequestParam("nameFilter") Optional<String> nameFilter,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("sortField") Optional<String> sortField,
                                    @RequestParam("sortDir") Optional<Sort.Direction> sortDir) {

        String sortBy;
        if (sortField.isPresent() && !sortField.get().isEmpty()) {
            sortBy = sortField.get();
        } else {
            sortBy = "id";
        }

        return categoryService.findAll(
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(10),
                sortBy,
                sortDir.orElse(Sort.Direction.ASC)
        );

    }

    @GetMapping("/{id}")
    public CategoryDto findOne(@PathVariable("id") Long id) {
        return categoryService.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            throw new IllegalArgumentException("For category creation id have to be null");
        }
        return categoryService.save(categoryDto);
    }


    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new IllegalArgumentException("For category update id have to be not null");
        }
        return categoryService.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(NotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto illegalArgumentException(IllegalArgumentException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
