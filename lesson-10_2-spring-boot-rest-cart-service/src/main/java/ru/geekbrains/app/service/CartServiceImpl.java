package ru.geekbrains.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.app.persist.Cart;
import ru.geekbrains.app.persist.CartRepository;
import ru.geekbrains.app.service.dto.CartDto;
import ru.geekbrains.app.service.dto.CartMapper;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDto save(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        cart = cartRepository.save(cart);
        return cartMapper.fromCart(cart);
    }

    @Override
    public Optional<CartDto> findById(Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::fromCart);
    }

    @Override
    public boolean existsById(Long id) {
        return cartRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
