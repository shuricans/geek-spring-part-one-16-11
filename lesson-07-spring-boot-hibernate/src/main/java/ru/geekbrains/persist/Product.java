package ru.geekbrains.persist;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(
            name = "products_sequence",
            sequenceName = "products_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "products_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @PositiveOrZero
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
}
