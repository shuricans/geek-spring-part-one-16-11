package ru.geekbrains.persist;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@ToString
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
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
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "product",
            orphanRemoval = true
    )
    @Column(nullable = false)
    private List<Price> prices;
}
