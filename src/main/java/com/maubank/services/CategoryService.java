package com.maubank.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maubank.domain.Category;
import com.maubank.domain.dtos.CategoriesDTO;
import com.maubank.domain.enums.Groups;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.CategorySpecification;
import com.maubank.repositories.CategoryRepository;
import com.maubank.utils.CommonFunctions;

@Service
public class CategoryService {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryRepository repository;

	public Category insert(Category category) {
		return this.repository.save(category.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public Category findById(Integer id) throws ObjectNotFoundException {
		Optional<Category> obj = this.repository.findById(id);

		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, Category.class.toString())
		));
	}

	public Page<Category> findAll(Integer user, String description, String group,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd,
			String status, Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(CategorySpecification.getSpecification(user, description, group,
				initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
				PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	public Category update(Category categoryUpdated) {
		Category category = this.findById(categoryUpdated.getId());

		category.setUser(categoryUpdated.getUser())
				.setDescription(categoryUpdated.getDescription())
				.setGroup(Groups.toEnum(categoryUpdated.getGroup()))
				.setUpdater("")
				.setUpdate(LocalDateTime.now());

		return this.repository.save(category);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {			
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("");
		}
	}

	public Category fromDTO(CategoriesDTO categoriesDTO) {
		return new Category().setId(categoriesDTO.getId())
							 .setUser(this.userService.findById(categoriesDTO.getUser()))
							 .setDescription(CommonFunctions.toUnicode(categoriesDTO.getDescription().toUpperCase()))
							 .setGroup(Groups.toEnum(categoriesDTO.getGroup().toUpperCase()));
	}

}
