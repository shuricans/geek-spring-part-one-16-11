package ru.geekbrains.app.service;

import ru.geekbrains.app.service.dto.CartDto;

import java.util.Optional;

public interface CartService {

    CartDto save(CartDto cartDto);

    Optional<CartDto> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
