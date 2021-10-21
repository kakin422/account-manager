package com.acmebank.accountmanager.service;

import java.util.List;

import com.acmebank.accountmanager.model.User;
import com.acmebank.accountmanager.model.Wallet;

public interface WalletService {
    Wallet findWalletByUser(User user);

    List<Wallet> findWalletsBySenderAndReceiverId(Long senderId, Long receiverId);

    void transferBetweenUsers(User sender, User receiver, Double amount) throws Exception;
}