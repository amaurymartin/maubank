package com.maubank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maubank.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	@Transactional(readOnly = true)
	Optional<User> findByUsername(String username);

	@Transactional(readOnly = true)
	Optional<User> findByEmail(String email);

	@Transactional(readOnly = true)
	Optional<User> findByCpf(String cpf);

}
