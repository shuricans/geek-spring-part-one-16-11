package ru.geekbrains.persist;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
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
    @Column(name = "price_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, updatable = false)
    private BigDecimal value;

    @Column(
            name = "start_date",
            nullable = false,
            updatable = false
    )
    private LocalDateTime startDate;

    @Column(
            name = "end_date",
            nullable = false
    )
    private LocalDateTime endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Price price = (Price) o;
        return id != null && Objects.equals(id, price.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
