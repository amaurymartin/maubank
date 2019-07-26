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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.maubank.domain.enums.Status;
import com.maubank.utils.Constants;

@Entity
@Table(name="tb_bdg",
	uniqueConstraints={@UniqueConstraint(columnNames = {"id_cat", "vl_bdg_yea"})}
)
public class Budget implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_bdg")
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cat")
	private Category category;

	@Column(name = "vl_bdg_yea", columnDefinition = "integer", nullable = false)
	private Integer year;

	@Column(name = "vl_bdg_jan", columnDefinition = "decimal(11,2)")
	private BigDecimal january;

	@Column(name = "vl_bdg_feb", columnDefinition = "decimal(11,2)")
	private BigDecimal february;

	@Column(name = "vl_bdg_mar", columnDefinition = "decimal(11,2)")
	private BigDecimal march;

	@Column(name = "vl_bdg_apr", columnDefinition = "decimal(11,2)")
	private BigDecimal april;

	@Column(name = "vl_bdg_may", columnDefinition = "decimal(11,2)")
	private BigDecimal may;

	@Column(name = "vl_bdg_jun", columnDefinition = "decimal(11,2)")
	private BigDecimal june;

	@Column(name = "vl_bdg_jul", columnDefinition = "decimal(11,2)")
	private BigDecimal july;

	@Column(name = "vl_bdg_aug", columnDefinition = "decimal(11,2)")
	private BigDecimal august;

	@Column(name = "vl_bdg_sep", columnDefinition = "decimal(11,2)")
	private BigDecimal september;

	@Column(name = "vl_bdg_oct", columnDefinition = "decimal(11,2)")
	private BigDecimal october;

	@Column(name = "vl_bdg_nov", columnDefinition = "decimal(11,2)")
	private BigDecimal november;

	@Column(name = "vl_bdg_dec", columnDefinition = "decimal(11,2)")
	private BigDecimal december;

	@Column(name = "dt_ini", columnDefinition = "date default current_date", nullable = false)
	private LocalDate initial;

	@Column(name = "dt_end", columnDefinition = "date default '9999-12-31' ", nullable = false)
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

	public Budget() {
		this.setInitial(LocalDate.now().withDayOfMonth(1))
			.setEnd(LocalDate.parse(Constants.END_DATE))
			.setInserter("")
			.setInsert(LocalDateTime.now())
			.setUpdater("")
			.setUpdate(LocalDateTime.now())
			.setStatus(Status.PENDING.getCode());
	}

	public Integer getId() {
		return id;
	}

	public Budget setId(Integer id) {
		this.id = id;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public Budget setCategory(Category category) {
		this.category = category;
		return this;
	}

	public Integer getYear() {
		return year;
	}

	public Budget setYear(Integer year) {
		this.year = year;
		return this;
	}

	public BigDecimal getJanuary() {
		return january;
	}

	public Budget setJanuary(BigDecimal january) {
		this.january = january;
		return this;
	}

	public BigDecimal getFebruary() {
		return february;
	}

	public Budget setFebruary(BigDecimal february) {
		this.february = february;
		return this;
	}

	public BigDecimal getMarch() {
		return march;
	}

	public Budget setMarch(BigDecimal march) {
		this.march = march;
		return this;
	}

	public BigDecimal getApril() {
		return april;
	}

	public Budget setApril(BigDecimal april) {
		this.april = april;
		return this;
	}

	public BigDecimal getMay() {
		return may;
	}

	public Budget setMay(BigDecimal may) {
		this.may = may;
		return this;
	}

	public BigDecimal getJune() {
		return june;
	}

	public Budget setJune(BigDecimal june) {
		this.june = june;
		return this;
	}

	public BigDecimal getJuly() {
		return july;
	}

	public Budget setJuly(BigDecimal july) {
		this.july = july;
		return this;
	}

	public BigDecimal getAugust() {
		return august;
	}

	public Budget setAugust(BigDecimal august) {
		this.august = august;
		return this;
	}

	public BigDecimal getSeptember() {
		return september;
	}

	public Budget setSeptember(BigDecimal september) {
		this.september = september;
		return this;
	}

	public BigDecimal getOctober() {
		return october;
	}

	public Budget setOctober(BigDecimal october) {
		this.october = october;
		return this;
	}

	public BigDecimal getNovember() {
		return november;
	}

	public Budget setNovember(BigDecimal november) {
		this.november = november;
		return this;
	}

	public BigDecimal getDecember() {
		return december;
	}

	public Budget setDecember(BigDecimal december) {
		this.december = december;
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public Budget setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public Budget setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public String getInserter() {
		return inserter;
	}

	public Budget setInserter(String inserter) {
		this.inserter = inserter;
		return this;
	}

	public LocalDateTime getInsert() {
		return insert;
	}

	public Budget setInsert(LocalDateTime insert) {
		this.insert = insert;
		return this;
	}

	public String getUpdater() {
		return updater;
	}

	public Budget setUpdater(String updater) {
		this.updater = updater;
		return this;
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public Budget setUpdate(LocalDateTime update) {
		this.update = update;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Budget setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", category=" + category + ", year=" + year + ", january=" + january + ", february="
				+ february + ", march=" + march + ", april=" + april + ", may=" + may + ", june=" + june + ", july="
				+ july + ", august=" + august + ", september=" + september + ", october=" + october + ", november="
				+ november + ", december=" + december + ", initial=" + initial + ", end=" + end + ", inserter="
				+ inserter + ", insert=" + insert + ", updater=" + updater + ", update=" + update + ", status=" + status
				+ "]";
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
		Budget other = (Budget) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
