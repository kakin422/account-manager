package com.acmebank.accountmanager.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acmebank.accountmanager.dto.WalletData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "wallets")
@NoArgsConstructor
public class Wallet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    @NotNull
    private User user;

    @Column(name = "balance", columnDefinition = "Decimal(10,2) default '1000000.00'")
    private BigDecimal balance;

    public WalletData toData() {
        WalletData walletData = new WalletData();

        walletData.setId(id);
        walletData.setUser(user);
        walletData.setBalance(balance);

        return walletData;
    }

    public Wallet fromData(WalletData walletData) {
        Wallet wallet = new Wallet();

        wallet.setId(walletData.getId());
        wallet.setUser(walletData.getUser());
        wallet.setBalance(walletData.getBalance());

        return wallet;
    }
}