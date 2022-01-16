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
@Table(name = "usr")
public class User {

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "users_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Order> orders;
}
