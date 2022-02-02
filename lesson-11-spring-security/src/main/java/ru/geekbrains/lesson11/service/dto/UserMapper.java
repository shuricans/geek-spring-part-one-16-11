package ru.geekbrains.lesson11.service.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.geekbrains.lesson11.persist.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);
}
