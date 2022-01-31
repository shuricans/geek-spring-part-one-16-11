package ru.geekbrains.app.service.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.app.persist.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    @Mapping(source = "cartId", target = "cart.id")
    Item toItem(ItemDto itemDto);

    @Mapping(source = "cart.id", target = "cartId")
    ItemDto fromItem(Item item);
}
