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

import com.maubank.domain.Wallet;
import com.maubank.domain.dtos.WalletsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.WalletSpecification;
import com.maubank.repositories.WalletRepository;
import com.maubank.utils.CommonFunctions;

@Service
public class WalletService {

	@Autowired
	private UserService userService;

	@Autowired
	private WalletRepository repository;

	public Wallet insert(Wallet wallet) {
		return this.repository.save(wallet.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public Wallet findById(Integer id) throws ObjectNotFoundException {
		Optional<Wallet> obj = this.repository.findById(id);
		
		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, Wallet.class.toString())
		));
	}

	public Page<Wallet> findAll(Integer user, String description, BigDecimal balanceIni, BigDecimal balanceEnd,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd,
			String status, Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(WalletSpecification.getSpecification(user, description, balanceIni, balanceEnd,
				initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
				PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	public Wallet update(Wallet walletUpdated) {
		Wallet wallet = this.findById(walletUpdated.getId());

		wallet.setDescription(walletUpdated.getDescription())
		      .setBalance(walletUpdated.getBalance())
		      .setUpdater("")
		      .setUpdate(LocalDateTime.now());

		return this.repository.save(wallet);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {			
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("DataIntegrityViolationException");
		}
	}

	public Wallet fromDTO(WalletsDTO walletsDTO) {
		return new Wallet().setUser(this.userService.findById(walletsDTO.getUser()))
						   .setDescription(CommonFunctions.toUnicode(walletsDTO.getDescription().toUpperCase()))
						   .setBalance(walletsDTO.getBalance());
	}

}
