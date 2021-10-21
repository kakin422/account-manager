package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.model.User;
import com.acmebank.accountmanager.model.Wallet;
import com.acmebank.accountmanager.protocol.BalanceResponse;
import com.acmebank.accountmanager.service.UserService;
import com.acmebank.accountmanager.service.WalletService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userSerivce;

    @Autowired
    WalletService walletSerivce;

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponse> handlerUserBalance(@RequestParam String username) {
        try {
            User user = userSerivce.findUserByUsername(username);

            if (user == null) {
                throw new Exception("user not found");
            }

            Wallet wallet = walletSerivce.findWalletByUser(user);

            if (wallet == null) {
                throw new Exception("wallet not found");
            }

            BalanceResponse responseData = new BalanceResponse();
            responseData.setBalance(wallet.getBalance());

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception exception) {
            BalanceResponse responseData = new BalanceResponse();
            responseData.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
