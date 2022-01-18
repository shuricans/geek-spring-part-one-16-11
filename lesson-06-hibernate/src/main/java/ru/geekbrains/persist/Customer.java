package ru.geekbrains.persist;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customers_sequence",
            sequenceName = "customers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "customers_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "customer_id")
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Order> orders;
}
