package ru.geekbrains.lesson_12.service;

import ru.geekbrains.lesson_12.service.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleDto> findAll();

    Optional<RoleDto> findById(long id);

    void deleteById(Long id);

    RoleDto save(RoleDto roleDto);

    Optional<RoleDto> findByName(String name);
}
