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

import com.maubank.domain.User;
import com.maubank.domain.dtos.UsersDTO;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.UserSpecification;
import com.maubank.repositories.UserRepository;
import com.maubank.utils.CommonFunctions;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User insert(User user) {
		return this.repository.save(user.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public User findById(Integer id) throws ObjectNotFoundException {
		Optional<User> obj = this.repository.findById(id);

		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, User.class.toString())
		));
	}

	public Page<User> findAll(String username, String email, String name, String surname, String cpf, LocalDate birthIni, LocalDate birthEnd,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd, String status,
			Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(
				UserSpecification.getSpecification(username, email, name, surname, cpf, birthIni, birthEnd,
						initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
					PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	public User update(User userUpdated) {
		User user = this.findById(userUpdated.getId());

		user.setUsername(userUpdated.getUsername())
			.setEmail(userUpdated.getEmail())
			.setName(userUpdated.getName())
			.setSurname(userUpdated.getSurname())
			.setCpf(userUpdated.getCpf())
			.setBirth(userUpdated.getBirth())
			.setPassword(userUpdated.getPassword())
			.setUpdater("")
			.setUpdate(LocalDateTime.now());

		return this.repository.save(user);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {			
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("DataIntegrityViolationException");
		}
	}

	public User fromDTO(UsersDTO usersDTO) {
		return new User().setId(usersDTO.getId())
						 .setUsername(usersDTO.getUsername().toLowerCase())
						 .setEmail(usersDTO.getEmail().toLowerCase())
						 .setName(CommonFunctions.toUnicode(usersDTO.getName().toUpperCase()))
						 .setSurname(usersDTO.getSurname() == null ? null : CommonFunctions.toUnicode(usersDTO.getSurname().toUpperCase()))
						 .setCpf(usersDTO.getCpf() == null ? null : usersDTO.getCpf().replaceAll("\\D", ""))
						 .setBirth(usersDTO.getBirth())
						 .setPassword(usersDTO.getPassword());
	}

}
