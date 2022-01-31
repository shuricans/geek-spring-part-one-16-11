package ru.geekbrains.app.persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @SequenceGenerator(
            name = "carts_sequence",
            sequenceName = "carts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "carts_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(
            mappedBy = "cart",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<Item> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return id != null && Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



















