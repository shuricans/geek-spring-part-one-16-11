package ru.geekbrains.lesson11.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson11.persist.Role;
import ru.geekbrains.lesson11.persist.RoleRepository;
import ru.geekbrains.lesson11.service.dto.RoleDto;
import ru.geekbrains.lesson11.service.dto.RoleMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::fromRole)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(long id) {
        return roleRepository.findById(id).map(roleMapper::fromRole);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        role = roleRepository.save(role);
        return roleMapper.fromRole(role);
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        return roleRepository.findByName(name).map(roleMapper::fromRole);
    }
}
