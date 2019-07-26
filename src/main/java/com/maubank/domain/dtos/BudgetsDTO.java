package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.maubank.domain.Budget;
import com.maubank.services.validations.BudgetValid;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

@BudgetValid
public class BudgetsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private Integer category;
	
	@Min(1)
	@Max(9999)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private Integer year;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal january;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal february;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal march;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal april;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal may;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal june;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal july;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal august;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal september;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal october;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal november;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	private BigDecimal december;

	public BudgetsDTO() {
	}

	public BudgetsDTO(Budget budget) {
		this.setId(budget.getId())
			.setCategory(budget.getCategory().getId())
			.setYear(budget.getYear())
			.setJanuary(budget.getJanuary())
			.setFebruary(budget.getFebruary())
			.setMarch(budget.getMarch())
			.setApril(budget.getApril())
			.setMay(budget.getMay())
			.setJune(budget.getJune())
			.setJuly(budget.getJuly())
			.setAugust(budget.getAugust())
			.setSeptember(budget.getSeptember())
			.setOctober(budget.getOctober())
			.setNovember(budget.getNovember())
			.setDecember(budget.getDecember());
			
	}

	public Integer getId() {
		return id;
	}

	public BudgetsDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCategory() {
		return category;
	}

	public BudgetsDTO setCategory(Integer category) {
		this.category = category;
		return this;
	}

	public Integer getYear() {
		return year;
	}

	public BudgetsDTO setYear(Integer year) {
		this.year = year;
		return this;
	}

	public BigDecimal getJanuary() {
		return january;
	}

	public BudgetsDTO setJanuary(BigDecimal january) {
		this.january = january;
		return this;
	}

	public BigDecimal getFebruary() {
		return february;
	}

	public BudgetsDTO setFebruary(BigDecimal february) {
		this.february = february;
		return this;
	}

	public BigDecimal getMarch() {
		return march;
	}

	public BudgetsDTO setMarch(BigDecimal march) {
		this.march = march;
		return this;
	}

	public BigDecimal getApril() {
		return april;
	}

	public BudgetsDTO setApril(BigDecimal april) {
		this.april = april;
		return this;
	}

	public BigDecimal getMay() {
		return may;
	}

	public BudgetsDTO setMay(BigDecimal may) {
		this.may = may;
		return this;
	}

	public BigDecimal getJune() {
		return june;
	}

	public BudgetsDTO setJune(BigDecimal june) {
		this.june = june;
		return this;
	}

	public BigDecimal getJuly() {
		return july;
	}

	public BudgetsDTO setJuly(BigDecimal july) {
		this.july = july;
		return this;
	}

	public BigDecimal getAugust() {
		return august;
	}

	public BudgetsDTO setAugust(BigDecimal august) {
		this.august = august;
		return this;
	}

	public BigDecimal getSeptember() {
		return september;
	}

	public BudgetsDTO setSeptember(BigDecimal september) {
		this.september = september;
		return this;
	}

	public BigDecimal getOctober() {
		return october;
	}

	public BudgetsDTO setOctober(BigDecimal october) {
		this.october = october;
		return this;
	}

	public BigDecimal getNovember() {
		return november;
	}

	public BudgetsDTO setNovember(BigDecimal november) {
		this.november = november;
		return this;
	}

	public BigDecimal getDecember() {
		return december;
	}

	public BudgetsDTO setDecember(BigDecimal december) {
		this.december = december;
		return this;
	}

}
