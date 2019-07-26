package com.maubank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maubank.domain.Goal;
import com.maubank.domain.User;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer>, JpaSpecificationExecutor<Goal>  {

	@Transactional(readOnly = true)
	Optional<Goal> findByUserAndDescription(User user, String description);

}
