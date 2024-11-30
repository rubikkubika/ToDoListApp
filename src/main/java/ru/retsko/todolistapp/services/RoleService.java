package ru.retsko.todolistapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.retsko.todolistapp.model.entities.Role;
import ru.retsko.todolistapp.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
