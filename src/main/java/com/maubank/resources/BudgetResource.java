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

import com.maubank.domain.dtos.BudgetDTO;
import com.maubank.domain.dtos.BudgetsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.services.BudgetService;
import com.maubank.utils.Constants;

@Validated
@RestController
@RequestMapping(value="/budgets")
public class BudgetResource {

	@Autowired
	private BudgetService service;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody BudgetsDTO budgetsDTO) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(this.service.insert(this.service.fromDTO(budgetsDTO)).getId())
											 .toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BudgetDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new BudgetDTO(this.service.findById(id)));
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Page<BudgetsDTO>> find(
			@RequestParam(value = "category", required=false) Integer category,
			@RequestParam(value = "yearIni", required=false) Integer yearIni,
			@RequestParam(value = "yearEnd", required=false) Integer yearEnd,
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
			@RequestParam(value = "orderBy", defaultValue="category") String orderBy,
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		return ResponseEntity.ok()
							 .body(this.service.findAll(category, yearIni, yearEnd, valueIni, valueEnd,
									 initialIni, initialEnd, endIni, endEnd,
									 insertIni, insertEnd,updateIni, updateEnd, status,
									 page, linesPerPage, orderBy, direction)
							 .map(budget -> new BudgetsDTO(budget)));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody BudgetsDTO budgetsDTO) {
		this.service.update(this.service.fromDTO(budgetsDTO).setId(id));

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.delete(id);

		return ResponseEntity.noContent().build();
	}

	/*TODO:
		- Criar endpoint para acessar o valor do orçamento de um certo mês(passado, presente, futuro) de uma certa categoria
	*/

}
