package com.maubank.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maubank.domain.dtos.GoalDTO;
import com.maubank.domain.dtos.GoalsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.services.GoalService;
import com.maubank.utils.Constants;

@Validated
@RestController
@RequestMapping(value="/goals")
public class GoalResource {

	@Autowired
	private GoalService service;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody GoalsDTO goalsDTO) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(this.service.insert(this.service.fromDTO(goalsDTO)).getId())
											 .toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<GoalDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new GoalDTO(this.service.findById(id)));
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Page<GoalsDTO>> find(
			@RequestParam(value = "user", required=false) Integer user,
			@RequestParam(value = "description", required=false) String description,
			@RequestParam(value = "valueIni", required=false) BigDecimal valueIni,
			@RequestParam(value = "valueEnd", required=false) BigDecimal valueEnd,
			@RequestParam(value = "initialIni", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate initialIni,
			@RequestParam(value = "initialEnd", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate initialEnd,
			@RequestParam(value = "endIni", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate endIni,
			@RequestParam(value = "endEnd", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate endEnd,
			@RequestParam(value = "insertIni", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT) LocalDateTime insertIni,
			@RequestParam(value = "insertEnd", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT) LocalDateTime insertEnd,
			@RequestParam(value = "updateIni", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT) LocalDateTime updateIni,
			@RequestParam(value = "updateEnd", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT) LocalDateTime updateEnd,
			@RequestParam(value = "status", required=false, defaultValue="CF") @Pattern(regexp = Status.STATUS_CODES) String status,
			@RequestParam(value = "page", defaultValue="0") Integer page,
			@RequestParam(value = "size", defaultValue="42") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue="user") String orderBy,
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		return ResponseEntity.ok()
							 .body(this.service.findAll(user, description, valueIni, valueEnd,
									 initialIni, initialEnd, endIni, endEnd,
									 insertIni, insertEnd, updateIni, updateEnd, status,
									 page, linesPerPage, orderBy, direction)
							 .map(goal -> new GoalsDTO(goal)));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody GoalsDTO goalsDTO) {
		this.service.update(this.service.fromDTO(goalsDTO).setId(id));

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.delete(id);

		return ResponseEntity.noContent().build();
	}

	/*TODO:
		- Criar endpoint para recuperar uma lista de metas que estão ativas em uma certa data (passado, presente, futuro)
		- Avaliar se essa data também pode ser um intervalo
	*/

}
