package ru.geekbrains.app.service.dto;

import lombok.*;

import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDto {

    private Long id;
    Set<ItemDto> items;
}
