package com.maubank.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maubank.domain.enums.Status;
import com.maubank.utils.Constants;

@Entity
@Table(name="tb_usr")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usr")
	private Integer id;

	@Column(name = "tx_usr_usn", columnDefinition = "varchar", nullable = false, unique = true)
	private String username;

	@Column(name = "tx_usr_ema", columnDefinition = "varchar", nullable = false, unique = true)
	private String email;

	@Column(name = "tx_usr_nam", columnDefinition = "varchar", nullable = false)
	private String name;

	@Column(name = "tx_usr_sur", columnDefinition = "varchar")
	private String surname;

	@Column(name = "tx_usr_cpf", columnDefinition = "varchar", unique = true)
	private String cpf;

	@Column(name = "dt_usr_bir", columnDefinition = "date")
	private LocalDate birth;

	@Column(name = "tx_usr_psw", columnDefinition = "varchar", nullable = false)
	private String password;

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

	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Wallet> wallets = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Category> categories = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Goal> goals = new ArrayList<>();

	public User() {
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

	public User setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public User setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public User setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public User setBirth(LocalDate birth) {
		this.birth = birth;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public User setWallets(List<Wallet> wallets) {
		this.wallets = wallets == null ? new ArrayList<Wallet>() : wallets;
		return this;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public User setCategories(List<Category> categories) {
		this.categories = categories == null ? new ArrayList<Category>() : categories;
		return this;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public User setGoals(List<Goal> goals) {
		this.goals = goals == null ? new ArrayList<Goal>() : goals;
		return this;
	}

	public LocalDate getInitial() {
		return initial;
	}

	public User setInitial(LocalDate initial) {
		this.initial = initial;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public User setEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public String getInserter() {
		return inserter;
	}

	public User setInserter(String inserter) {
		this.inserter = inserter;
		return this;
	}

	public LocalDateTime getInsert() {
		return insert;
	}

	public User setInsert(LocalDateTime insert) {
		this.insert = insert;
		return this;
	}

	public String getUpdater() {
		return updater;
	}

	public User setUpdater(String updater) {
		this.updater = updater;
		return this;
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public User setUpdate(LocalDateTime update) {
		this.update = update;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public User setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", name=" + name + ", surname="
				+ surname + ", cpf=" + cpf + ", birth=" + birth + ", password=" + password + ", initial=" + initial
				+ ", end=" + end + ", inserter=" + inserter + ", insert=" + insert + ", updater=" + updater
				+ ", update=" + update + ", status=" + status + ", wallets=" + wallets + ", categories=" + categories
				+ ", goals=" + goals + "]";
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
