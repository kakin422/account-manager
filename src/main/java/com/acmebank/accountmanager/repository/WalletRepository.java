package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.model.*;
import java.util.Optional;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "1000") })
    public Optional<Wallet> findByUser(User user);

    @Query(value = "SELECT * FROM wallets where user_id = ?1 or user_id = ?2 for update", nativeQuery = true)
    public List<Wallet> findAllWalletsBySenderAndReceiverId(Long senderId, Long receiverId);
}