package com.maubank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maubank.domain.Budget;
import com.maubank.domain.Category;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>, JpaSpecificationExecutor<Budget> {

	Optional<Budget> findByCategoryAndYear(Category category, Integer year);
}
