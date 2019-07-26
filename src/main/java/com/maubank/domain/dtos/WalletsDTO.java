package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Wallet;
import com.maubank.services.validations.WalletValid;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

@WalletValid
public class WalletsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private Integer user;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private BigDecimal balance;

	public WalletsDTO() {
	}

	public WalletsDTO(Wallet wallet) {
		this.setId(wallet.getId())
			.setUser(wallet.getUser().getId())
			.setDescription(wallet.getDescription())
			.setBalance(wallet.getBalance());
	}

	public Integer getId() {
		return id;
	}

	public WalletsDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUser() {
		return user;
	}

	public WalletsDTO setUser(Integer user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public WalletsDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public WalletsDTO setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

}
