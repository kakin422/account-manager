package com.acmebank.accountmanager.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import com.acmebank.accountmanager.model.User;
import com.acmebank.accountmanager.repository.UserRepository;
import com.acmebank.accountmanager.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        Optional<User> userResult = userRepository.findByUsername(username);
        if (userResult.isEmpty()) {
            return null;
        }

        return userResult.get();
    }
}
