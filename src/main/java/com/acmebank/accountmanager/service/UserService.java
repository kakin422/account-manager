package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.model.User;

public interface UserService {
    User findUserByUsername(String username);
}
