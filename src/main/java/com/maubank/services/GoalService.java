package com.maubank.services;

import java.math.BigDecimal;
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

import com.maubank.domain.Goal;
import com.maubank.domain.dtos.GoalsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.GoalSpecification;
import com.maubank.repositories.GoalRepository;
import com.maubank.utils.CommonFunctions;

@Service
public class GoalService {

	@Autowired
	private UserService userService;

	@Autowired
	private GoalRepository repository;

	public Goal insert(Goal goal) {
		return this.repository.save(goal.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public Goal findById(Integer id) throws ObjectNotFoundException {
		Optional<Goal> obj = this.repository.findById(id);

		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, Goal.class.toString())
		));
	}

	public Page<Goal> findAll(Integer user, String description, BigDecimal valueIni, BigDecimal valueEnd,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd, LocalDateTime insertIni, LocalDateTime insertEnd,
			LocalDateTime updateIni, LocalDateTime updateEnd, String status,
			Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(GoalSpecification.getSpecification(user, description, valueIni, valueEnd,
				initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
				PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	public Goal update(Goal goalUpdated) {
		Goal goal = this.findById(goalUpdated.getId());

		goal.setDescription(goalUpdated.getDescription())
			.setValue(goalUpdated.getValue())
			.setInitial(goalUpdated.getInitial())
			.setEnd(goalUpdated.getEnd())
			.setUpdater("")
			.setUpdate(LocalDateTime.now());

		return this.repository.save(goal);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {			
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("DataIntegrityViolationException");
		}
	}

	public Goal fromDTO(GoalsDTO goalsDTO) {
		return new Goal().setId(goalsDTO.getId())
						 .setUser(this.userService.findById(goalsDTO.getUser()))
						 .setDescription(CommonFunctions.toUnicode(goalsDTO.getDescription().toUpperCase()))
						 .setValue(goalsDTO.getValue())
						 .setInitial(goalsDTO.getInitial())
						 .setEnd(goalsDTO.getEnd());
	}

}
