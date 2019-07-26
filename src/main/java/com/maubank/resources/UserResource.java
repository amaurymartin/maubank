package com.maubank.resources;

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

import com.maubank.domain.dtos.UserDTO;
import com.maubank.domain.dtos.UsersDTO;
import com.maubank.domain.enums.Status;
import com.maubank.services.UserService;
import com.maubank.utils.Constants;

@Validated
@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsersDTO usersDTO) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(this.service.insert(this.service.fromDTO(usersDTO)).getId())
											 .toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new UserDTO(this.service.findById(id)));
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<Page<UsersDTO>> find(
			@RequestParam(value = "username", required=false) String username,
			@RequestParam(value = "email", required=false) String email,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "surname", required=false) String surname,
			@RequestParam(value = "cpf", required=false) String cpf,
			@RequestParam(value = "birthIni", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate birthIni,
			@RequestParam(value = "birthEnd", required=false) @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT) LocalDate birthEnd,
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
			@RequestParam(value = "orderBy", defaultValue="name") String orderBy,
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		return ResponseEntity.ok()
							 .body(this.service.findAll(username, email, name, surname, cpf, birthIni, birthEnd,
									 initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd,
									 updateIni, updateEnd, status, page, linesPerPage, orderBy, direction)
							 .map(user -> new UsersDTO(user.setPassword(null))));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody UsersDTO usersDTO) {
		this.service.update(this.service.fromDTO(usersDTO).setId(id));

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.delete(id);

		return ResponseEntity.noContent().build();
	}

	/*TODO:
		- Implementar o Spring Secutiry para restringir o acesso aos endpoints
		- Usar uma criptografia para a senha do usuário
		- Avaliar usar criptografia para outros dados sensíveis
		- Criar um endpoint para recuperar uma lista de usuários com o status está cancelado
		- Criar um endpoint para recuperar uma lista de usuários cujo status está ativo e o dt_end vai até today + 1 mês
		- Criar um endpoint para atualizar o dt_end para today + 1 mês (renovar assinatura)
		- Criar um endpoint para atualizar o dt_end para today e alterar o status para cancelado (cancelar assinatura)
	*/

}
