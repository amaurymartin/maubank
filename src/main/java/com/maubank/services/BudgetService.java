package com.maubank.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maubank.domain.Budget;
import com.maubank.domain.dtos.BudgetsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.BudgetSpecification;
import com.maubank.repositories.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BudgetRepository repository;

	public Budget insert(Budget budget) {
		return this.repository.save(budget.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public Budget findById(Integer id) throws ObjectNotFoundException {
		Optional<Budget> obj = this.repository.findById(id);

		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, Budget.class.toString())
		));
	}

	public Page<Budget> findAll(Integer category, Integer yearIni, Integer yearEnd, BigDecimal valueIni, BigDecimal valueEnd,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd,
			String status, Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(BudgetSpecification.getSpecification(category, yearIni, yearEnd, valueIni, valueEnd,
				initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
				PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	public Budget update(Budget budgetUpdated) {
		Budget budget = this.findById(budgetUpdated.getId());

		budget.setCategory(budgetUpdated.getCategory())
			  .setYear(budgetUpdated.getYear())
			  .setJanuary(budgetUpdated.getJanuary())
			  .setFebruary(budgetUpdated.getFebruary())
			  .setMarch(budgetUpdated.getMarch())
			  .setApril(budgetUpdated.getApril())
			  .setMay(budgetUpdated.getMay())
			  .setJune(budgetUpdated.getJune())
			  .setJuly(budgetUpdated.getJuly())
			  .setAugust(budgetUpdated.getAugust())
			  .setSeptember(budgetUpdated.getSeptember())
			  .setOctober(budgetUpdated.getOctober())
			  .setNovember(budgetUpdated.getNovember())
			  .setDecember(budgetUpdated.getDecember())
			  .setInitial(LocalDate.of(budgetUpdated.getYear(), Month.JANUARY, 1))
			  .setEnd(LocalDate.of(budgetUpdated.getYear(), Month.DECEMBER, 31))
			  .setUpdater("")
			  .setUpdate(LocalDateTime.now());

		return this.repository.save(budget);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {			
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("");
		}
	}

	public Budget fromDTO(BudgetsDTO budgetsDTO) {
		return new Budget().setId(budgetsDTO.getId())
						   .setCategory(this.categoryService.findById(budgetsDTO.getCategory()))
						   .setYear(budgetsDTO.getYear())
						   .setJanuary(budgetsDTO.getJanuary())
						   .setFebruary(budgetsDTO.getFebruary())
						   .setMarch(budgetsDTO.getMarch())
						   .setApril(budgetsDTO.getApril())
						   .setMay(budgetsDTO.getMay())
						   .setJune(budgetsDTO.getJune())
						   .setJuly(budgetsDTO.getJuly())
						   .setAugust(budgetsDTO.getAugust())
						   .setSeptember(budgetsDTO.getSeptember())
						   .setOctober(budgetsDTO.getOctober())
						   .setNovember(budgetsDTO.getNovember())
						   .setDecember(budgetsDTO.getDecember())
						   .setInitial(LocalDate.of(budgetsDTO.getYear(), Month.JANUARY, 1))
						   .setEnd(LocalDate.of(budgetsDTO.getYear(), Month.DECEMBER, 31));
	}

}
