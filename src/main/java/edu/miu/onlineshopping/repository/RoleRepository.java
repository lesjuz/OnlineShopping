package edu.miu.onlineshopping.repository;

import edu.miu.onlineshopping.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);

    List<Role> findAll();
}
