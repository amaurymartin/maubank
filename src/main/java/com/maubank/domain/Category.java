package com.maubank.domain;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

import com.maubank.domain.enums.Groups;
import com.maubank.domain.enums.Status;
import com.maubank.utils.Constants;

@Entity
@Table(name="tb_cat",
	uniqueConstraints={@UniqueConstraint(columnNames = {"id_usr", "tx_cat_dsc"})}
)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cat")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_usr")
	private User user;

	@Column(name = "tx_cat_dsc", columnDefinition = "varchar", nullable = false)
	private String description;

	@Column(name = "tx_cat_grp", columnDefinition = "varchar", nullable = false)
	private String group;

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

	public Category() {
		this.setInitial(LocalDate.now())
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

	public Category setId(Integer id) {
		this.id = id;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Category setUser(User user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Category setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getGroup() {
		return Groups.toEnum(group).getCode();
	}

	public Category setGroup(Groups group) {
		this.group = group.getCode();
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public Category setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public Category setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public String getInserter() {
		return inserter;
	}

	public Category setInserter(String inserter) {
		this.inserter = inserter;
		return this;
	}

	public LocalDateTime getInsert() {
		return insert;
	}

	public Category setInsert(LocalDateTime insert) {
		this.insert = insert;
		return this;
	}

	public String getUpdater() {
		return updater;
	}

	public Category setUpdater(String updater) {
		this.updater = updater;
		return this;
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public Category setUpdate(LocalDateTime update) {
		this.update = update;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Category setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", user=" + user.getId() + ", description=" + description + ", group=" + group
				+ ", initial=" + initial + ", end=" + end + ", inserter=" + inserter + ", insert="
				+ insert + ", updater=" + updater + ", update=" + update + ", status=" + status + "]";
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
