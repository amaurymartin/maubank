package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Payment;
import com.maubank.services.validations.PaymentValid;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

@PaymentValid
public class PaymentsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	private Integer category;

	@NotNull
	private Integer wallet;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@DecimalMin(Constants.MINIMUM_NEGATIVE_VALUE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private BigDecimal value;
	
	@NotNull(message = Messages.MANDATORY_FIELD)
	private LocalDate date;

	public PaymentsDTO() {
	}

	public PaymentsDTO(Payment payment) {
		this.setId(payment.getId())
			.setCategory(payment.getCategory().getId())
			.setWallet(payment.getWallet().getId())
			.setDescription(payment.getDescription())
			.setValue(payment.getValue())
			.setDate(payment.getEnd());
	}

	public Integer getId() {
		return id;
	}

	public PaymentsDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCategory() {
		return category;
	}

	public PaymentsDTO setCategory(Integer category) {
		this.category = category;
		return this;
	}

	public Integer getWallet() {
		return wallet;
	}

	public PaymentsDTO setWallet(Integer wallet) {
		this.wallet = wallet;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public PaymentsDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getValue() {
		return value;
	}

	public PaymentsDTO setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public LocalDate getDate() {
		return date;
	}

	public PaymentsDTO setDate(LocalDate date) {
		this.date = date;
		return this;
	}

}
