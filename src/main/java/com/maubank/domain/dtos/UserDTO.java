package com.maubank.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.maubank.domain.User;

public class UserDTO extends UsersDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected List<WalletsDTO> wallets = new ArrayList<WalletsDTO>();

	protected List<CategoriesDTO> categories = new ArrayList<CategoriesDTO>();

	protected List<GoalsDTO> goals = new ArrayList<GoalsDTO>();

	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		super(user.setPassword(null));
		
		user.getWallets().forEach(wallet -> this.wallets.add(new WalletsDTO(wallet)));
		user.getCategories().forEach(category -> this.categories.add(new CategoriesDTO(category)));
		user.getGoals().forEach(goal -> this.goals.add(new GoalsDTO(goal)));
	}

	public List<WalletsDTO> getWallets() {
		return wallets;
	}

	public UserDTO setWallets(List<WalletsDTO> wallets) {
		this.wallets = wallets;
		return this;
	}

	public List<CategoriesDTO> getCategories() {
		return categories;
	}

	public UserDTO setCategories(List<CategoriesDTO> categories) {
		this.categories = categories;
		return this;
	}

	public List<GoalsDTO> getGoals() {
		return goals;
	}

	public UserDTO setGoals(List<GoalsDTO> goals) {
		this.goals = goals;
		return this;
	}

}
