package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.model.User;
import com.acmebank.accountmanager.protocol.TransferralRequest;
import com.acmebank.accountmanager.protocol.TransferralResponse;
import com.acmebank.accountmanager.service.UserService;
import com.acmebank.accountmanager.service.WalletService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferral")
public class TransferralController {
    Logger logger = LoggerFactory.getLogger(TransferralController.class);

    @Autowired
    UserService userSerivce;

    @Autowired
    WalletService walletService;

    @PostMapping("")
    public ResponseEntity<TransferralResponse> handleTransferral(@RequestBody TransferralRequest requestData) {
        try {
            requestData.validate();

            TransferralResponse responseData = new TransferralResponse();
            User sender = userSerivce.findUserByUsername(requestData.getSenderUsername());

            if (sender == null) {
                throw new Exception("sender not found");
            }

            User receiver = userSerivce.findUserByUsername(requestData.getReceiverUsername());
            if (receiver == null) {
                throw new Exception("receiver not found");
            }

            walletService.transferBetweenUsers(sender, receiver, requestData.getAmount());
            responseData.setMessage("Transfer success");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception exception) {
            TransferralResponse responseData = new TransferralResponse();
            responseData.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
