package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.domain.CartLine;
import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User findUserByEmail(String email);
    List<User> getAll();
    User updateUser(User user);
}
