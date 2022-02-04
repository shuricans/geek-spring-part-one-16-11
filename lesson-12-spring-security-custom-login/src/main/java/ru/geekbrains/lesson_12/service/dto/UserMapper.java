package ru.geekbrains.lesson_12.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.lesson_12.persist.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);
}
