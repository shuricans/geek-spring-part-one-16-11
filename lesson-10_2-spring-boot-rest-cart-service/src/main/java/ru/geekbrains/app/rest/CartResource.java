package ru.geekbrains.app.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.app.service.CartService;
import ru.geekbrains.app.service.ProductService;
import ru.geekbrains.app.service.dto.CartDto;
import ru.geekbrains.app.service.dto.ItemDto;
import ru.geekbrains.app.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartResource {

    private final CartService cartService;
    private final ProductService productService;

    private final static String INVALID_CART_ID = "Invalid cart id";
    private final static String INVALID_PRODUCT_ID = "Invalid product id";
    private final static String CART_WITH_ID_NOT_FOUND = "Cart with id = %d not found";
    private final static String PRODUCT_WITH_ID_NOT_FOUND = "Product with id = %d not found";

    @GetMapping("/{id}")
    public CartDto find(@PathVariable("id") Long id) {
        checkValidId(id, INVALID_CART_ID);
        return cartService.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format(CART_WITH_ID_NOT_FOUND, id)));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CartDto create() {
        return cartService.save(new CartDto());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        checkValidId(id, INVALID_CART_ID);
        if (!cartService.existsById(id)) {
            throw new NotFoundException(String.format(CART_WITH_ID_NOT_FOUND, id));
        }
        cartService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CartDto update(@PathVariable("id") Long id,
                          @RequestBody Set<ItemDto> items) {
        checkValidId(id, INVALID_CART_ID);
        CartDto cartDto = cartService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(CART_WITH_ID_NOT_FOUND, id)));

        // filtering, remove item if (item.quantity <= 0)
        items = items.stream()
                .filter(item -> item.getQuantity() == null || item.getQuantity() > 0)
                .collect(Collectors.toSet());

        // checking and setup incoming values
        items.forEach(item -> {
            // check product id
            Long productId = item.getProduct().getId();
            checkValidId(productId, INVALID_PRODUCT_ID);

            ProductDto productDto = productService.findById(productId)
                    .orElseThrow(() ->
                            new NotFoundException(String.format(PRODUCT_WITH_ID_NOT_FOUND, productId)));

            // setup default price to the item
            if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                item.setPrice(productDto.getPrice());
            }

            // setup default quantity to the item
            if (item.getQuantity() == null) {
                item.setQuantity(1);
            }

            item.setCartId(id);
        });

        cartDto.setItems(items);

        return cartService.save(cartDto);
    }

    private void checkValidId(Long id, String msg) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(msg);
        }
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
