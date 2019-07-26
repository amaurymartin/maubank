package com.maubank.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Wallet;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

public class WalletDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private UsersDTO user;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@DecimalMin(Constants.ZERO_VALUE)
	@DecimalMax(Constants.MAXIMUM_VALUE)
	@NotNull(message = Messages.MANDATORY_FIELD)
	private BigDecimal balance;

	public WalletDTO() {
	}

	public WalletDTO(Wallet wallet) {
		this.setId(wallet.getId())
			.setUser(new UsersDTO(wallet.getUser().setPassword(null)))
			.setDescription(wallet.getDescription())
			.setBalance(wallet.getBalance());
	}

	public Integer getId() {
		return id;
	}

	public WalletDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public UsersDTO getUser() {
		return user;
	}

	public WalletDTO setUser(UsersDTO user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public WalletDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public WalletDTO setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

}
