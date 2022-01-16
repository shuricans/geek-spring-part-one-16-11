package ru.geekbrains.persist;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @SequenceGenerator(
            name = "items_sequence",
            sequenceName = "items_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "items_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer count;

}
