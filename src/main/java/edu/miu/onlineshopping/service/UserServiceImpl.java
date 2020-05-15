package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.repository.AddressRepository;
import edu.miu.onlineshopping.repository.RoleRepository;
import edu.miu.onlineshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().contains("BUYER")){
            user.setActive(1);
        }

        return userRepository.save(user);
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }


    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }


}