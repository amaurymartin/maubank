package com.maubank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maubank.domain.User;
import com.maubank.domain.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>, JpaSpecificationExecutor<Wallet> {

	@Transactional(readOnly = true)
	Optional<Wallet> findByUserAndDescription(User user, String description);

}
