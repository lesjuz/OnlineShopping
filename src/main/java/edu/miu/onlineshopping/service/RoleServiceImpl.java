package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    @Override
    public Role get(Long id) {

        return roleRepository.findById(id).get();
    }

}
