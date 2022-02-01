package ru.geekbrains.lesson11.service.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryDto {

    private Long id;

    @NotBlank
    private String name;
}
