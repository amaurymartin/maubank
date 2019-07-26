package com.maubank.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.maubank.domain.enums.Status;

@Entity
@Table(name="tb_pay")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_pay")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_cat")
	private Category category;

	@OneToOne
	@JoinColumn(name = "id_wal")
	private Wallet wallet;

	@Column(name = "tx_pay_dsc", columnDefinition = "varchar", nullable = false)
	private String description;

	@Column(name = "vl_pay", columnDefinition = "decimal(11,2)", nullable = false)
	private BigDecimal value;

	@Column(name = "dt_ini", columnDefinition = "date default current_date", nullable = false)
	private LocalDate initial;

	@Column(name = "dt_end", columnDefinition = "date default current_date", nullable = false)
	private LocalDate end;

	@Column(name = "tx_ins", columnDefinition = "varchar default current_user", nullable = false)
	private String inserter;

	@Column(name = "ts_ins", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private LocalDateTime insert;

	@Column(name = "tx_upd", columnDefinition = "varchar default current_user", nullable = false)
	private String updater;

	@Column(name = "ts_upd", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private LocalDateTime update;

	@Column(name = "tx_sts", columnDefinition = "varchar(2) default 'PD' ", nullable = false)
	private String status;

	public Payment() {
		this.setInitial(LocalDate.now())
			.setEnd(LocalDate.now())
			.setInserter("")
			.setInsert(LocalDateTime.now())
			.setUpdater("")
			.setUpdate(LocalDateTime.now())
			.setStatus(Status.PENDING.getCode());
	}

	public Integer getId() {
		return id;
	}

	public Payment setId(Integer id) {
		this.id = id;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public Payment setCategory(Category category) {
		this.category = category;
		return this;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public Payment setWallet(Wallet wallet) {
		this.wallet = wallet;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Payment setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Payment setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public Payment setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public Payment setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public String getInserter() {
		return inserter;
	}

	public Payment setInserter(String inserter) {
		this.inserter = inserter;
		return this;
	}

	public LocalDateTime getInsert() {
		return insert;
	}

	public Payment setInsert(LocalDateTime insert) {
		this.insert = insert;
		return this;
	}

	public String getUpdater() {
		return updater;
	}

	public Payment setUpdater(String updater) {
		this.updater = updater;
		return this;
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public Payment setUpdate(LocalDateTime update) {
		this.update = update;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Payment setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", category=" + category.getId() + ", wallet=" + wallet.getId() + ", value=" + value
				+ ", initial=" + initial + ", end=" + end + ", inserter=" + inserter
				+ ", insert=" + insert + ", updater=" + updater + ", update=" + update + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
