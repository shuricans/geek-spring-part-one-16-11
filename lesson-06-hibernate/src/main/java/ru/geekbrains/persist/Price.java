package ru.geekbrains.persist;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price")
public class Price {

    @Id
    @SequenceGenerator(
            name = "prices_sequence",
            sequenceName = "prices_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "prices_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, updatable = false)
    private BigDecimal value;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
}
