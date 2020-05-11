package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    Role get(Long id);

}
