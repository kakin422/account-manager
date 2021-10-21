package com.acmebank.accountmanager.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.acmebank.accountmanager.model.User;
import com.acmebank.accountmanager.model.Wallet;
import com.acmebank.accountmanager.repository.WalletRepository;
import com.acmebank.accountmanager.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet findWalletByUser(User user) {
        Optional<Wallet> wallet = walletRepository.findByUser(user);

        if (wallet.isEmpty()) {
            return null;
        }

        return wallet.get();
    }

    @Override
    public List<Wallet> findWalletsBySenderAndReceiverId(Long senderId, Long receiverId) {
        List<Wallet> wallets = walletRepository.findAllWalletsBySenderAndReceiverId(senderId, receiverId);

        return wallets;
    }

    @Override
    public void transferBetweenUsers(User sender, User receiver, Double amount) throws Exception {
        List<Wallet> walletList = this.findWalletsBySenderAndReceiverId(sender.getId(), receiver.getId());
        Wallet senderWallet = walletList.stream().filter(wallet -> sender.getId().equals(wallet.getUser().getId()))
                .findFirst().orElse(null);
        if (senderWallet == null) {
            throw new Exception("sender wallet not found");
        }

        Wallet receiverWallet = walletList.stream().filter(wallet -> receiver.getId().equals(wallet.getUser().getId()))
                .findFirst().orElse(null);
        if (receiverWallet == null) {
            throw new Exception("receiver wallet not found");
        }

        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
        if (senderWallet.getBalance().compareTo(amountBigDecimal) < 0) {
            throw new Exception("sender has not enough money to transfer");
        }

        BigDecimal senderNewBalance = senderWallet.getBalance().subtract(amountBigDecimal);
        BigDecimal receiverNewBalance = receiverWallet.getBalance().add(amountBigDecimal);

        senderWallet.setBalance(senderNewBalance);
        receiverWallet.setBalance(receiverNewBalance);

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }

}
