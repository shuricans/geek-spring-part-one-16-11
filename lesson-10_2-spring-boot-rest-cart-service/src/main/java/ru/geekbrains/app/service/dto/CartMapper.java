package ru.geekbrains.app.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.app.persist.Cart;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface CartMapper {

    Cart toCart(CartDto cartDto);

    @InheritInverseConfiguration
    CartDto fromCart(Cart cart);
}
