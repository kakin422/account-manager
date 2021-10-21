package com.acmebank.accountmanager.dto;

import java.math.BigDecimal;

import com.acmebank.accountmanager.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletData {
    private Long id;

    private User user;

    private BigDecimal balance;
}
