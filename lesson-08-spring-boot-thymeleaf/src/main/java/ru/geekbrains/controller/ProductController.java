package ru.geekbrains.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecification;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("minPrice") Optional<BigDecimal> minPrice,
                           @RequestParam("maxPrice") Optional<BigDecimal> maxPrice) {

        // first way by @Query
//        List<Product> products;
//
//        String nameLike = null;
//        if (nameFilter.isPresent() && !nameFilter.get().isEmpty()) {
//            nameLike = "%" + nameFilter.get().toLowerCase(Locale.ROOT) + "%";
//        }
//
//        logger.info("Product filter, nameFilter = {}", nameLike);
//        logger.info("Product filter, minPrice = {}", minPrice.orElse(null));
//        logger.info("Product filter, maxPrice = {}", maxPrice.orElse(null));
//
//        products = productRepository.findByFilter(
//                nameLike,
//                minPrice.orElse(null),
//                maxPrice.orElse(null)
//        );
//
//        model.addAttribute("products", products);

        // second way by Specification
        Specification<Product> spec = null;

        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = Specification.where(ProductSpecification.nameLike(nameFilter.get().toLowerCase(Locale.ROOT)));
        }

        if (minPrice.isPresent()) {
            spec = combineSpec(spec, ProductSpecification.minPriceFilter(minPrice.get()));
        }

        if (maxPrice.isPresent()) {
            spec = combineSpec(spec, ProductSpecification.maxPriceFilter(maxPrice.get()));
        }

        spec = combineSpec(spec, Specification.where(null));

        model.addAttribute("products", productRepository.findAll(spec));
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("You will try to delete a product with = {}", id);
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Product with id = {} deleted successfully", id);
        } else {
            logger.info("id = {} does not exist", id);
        }
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }

    private <T> Specification<T> combineSpec(Specification<T> s1, Specification<T> s2) {
        return s1 == null ? Specification.where(s2) : s1.and(s2);
    }
}
