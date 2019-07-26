package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Goal;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

public class GoalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private UsersDTO user;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@DecimalMin(Constants.MINIMUM_POSITIVE_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private BigDecimal value;

	@FutureOrPresent(message = Messages.INVALID_DATE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private LocalDate initial;

	@Future(message = Messages.INVALID_DATE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private LocalDate end;

	public GoalDTO() {
	}

	public GoalDTO(Goal goal) {
		this.setId(goal.getId())
			.setUser(new UsersDTO(goal.getUser().setPassword(null)))
			.setDescription(goal.getDescription())
			.setValue(goal.getValue())
			.setInitial(goal.getInitial())
			.setEnd(goal.getEnd());
	}

	public Integer getId() {
		return id;
	}

	public GoalDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public UsersDTO getUser() {
		return user;
	}

	public GoalDTO setUser(UsersDTO user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public GoalDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getValue() {
		return value;
	}

	public GoalDTO setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public GoalDTO setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public GoalDTO setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

}
