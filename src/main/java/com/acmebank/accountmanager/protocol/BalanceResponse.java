package com.acmebank.accountmanager.protocol;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceResponse {
    private BigDecimal balance;

    private String message;
}
