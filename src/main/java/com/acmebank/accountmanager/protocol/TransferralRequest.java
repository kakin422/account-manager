package com.acmebank.accountmanager.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferralRequest {
    private String senderUsername;

    private String receiverUsername;

    private Double amount;

    public void validate() throws Exception {
        if (senderUsername == null) {
            throw new Exception("sender cannot be null");
        }

        if (receiverUsername == null) {
            throw new Exception("receiver cannot be null");
        }

        if (amount == null || amount <= 0) {
            throw new Exception("amount shoule larger than 0");
        }
    }
}
