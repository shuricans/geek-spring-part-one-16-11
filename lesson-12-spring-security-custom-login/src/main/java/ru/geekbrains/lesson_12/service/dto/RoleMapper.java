package ru.geekbrains.lesson_12.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.lesson_12.persist.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "users", ignore = true)
    Role toRole(RoleDto roleDto);

    @InheritInverseConfiguration
    RoleDto fromRole(Role role);
}
