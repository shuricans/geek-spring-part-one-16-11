package ru.geekbrains.app.service.dto;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemDto {

    private Long id;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
    private Long cartId;
}
