package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Payment;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	private CategoriesDTO category;

	@NotNull
	private WalletsDTO wallet;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@DecimalMin(Constants.MINIMUM_NEGATIVE_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private BigDecimal value;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private LocalDate initial;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private LocalDate end;

	public PaymentDTO() {
	}

	public PaymentDTO(Payment payment) {
		this.setId(payment.getId())
			.setCategory(new CategoriesDTO(payment.getCategory()))
			.setWallet(new WalletsDTO(payment.getWallet()))
			.setDescription(payment.getDescription())
			.setValue(payment.getValue())
			.setInitial(payment.getInitial())
			.setEnd(payment.getEnd());
	}

	public Integer getId() {
		return id;
	}

	public PaymentDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public CategoriesDTO getCategory() {
		return category;
	}

	public PaymentDTO setCategory(CategoriesDTO category) {
		this.category = category;
		return this;
	}

	public WalletsDTO getWallet() {
		return wallet;
	}

	public PaymentDTO setWallet(WalletsDTO wallet) {
		this.wallet = wallet;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public PaymentDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getValue() {
		return value;
	}

	public PaymentDTO setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public PaymentDTO setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public PaymentDTO setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

}
