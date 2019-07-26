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
import org.springframework.transaction.annotation.Transactional;

import com.maubank.domain.Payment;
import com.maubank.domain.dtos.PaymentsDTO;
import com.maubank.domain.enums.Status;
import com.maubank.domain.specifications.PaymentSpecification;
import com.maubank.repositories.PaymentRepository;
import com.maubank.utils.CommonFunctions;

@Service
public class PaymentService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private PaymentRepository repository;

	@Transactional
	public Payment insert(Payment payment) {
		if(!payment.getInitial().isAfter(LocalDate.now())) this.updateWalletBalance(payment);

		return this.repository.save(payment.setId(null).setStatus(Status.CONFIRMED.getCode()));
	}

	public Payment findById(Integer id) throws ObjectNotFoundException {
		Optional<Payment> obj = this.repository.findById(id);

		return obj.orElseThrow(() -> (
			new ObjectNotFoundException(id, Payment.class.toString())
		));
	}

	public Page<Payment> findAll(Integer category, Integer wallet, String description, BigDecimal valueIni, BigDecimal valueEnd,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd,
			String status, Integer page, Integer linesPerPage, String orderBy, String direction) {
		return this.repository.findAll(PaymentSpecification.getSpecification(category, wallet, description, valueIni, valueEnd,
				initialIni, initialEnd, endIni, endEnd, insertIni, insertEnd, updateIni, updateEnd, status),
				PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy));
	}

	@Transactional
	public Payment update(Payment paymentUpdated) {
		Payment payment = this.findById(paymentUpdated.getId());

		if(paymentUpdated.getEnd().isBefore(LocalDate.now())) {
			if(paymentUpdated.getWallet().equals(payment.getWallet())) {
				if(paymentUpdated.getValue().compareTo(payment.getValue()) != 0)
					this.updateWalletBalance(payment.setValue(paymentUpdated.getValue().subtract(payment.getValue())));
			} else {
				this.updateWalletBalance(payment.setValue(payment.getValue().multiply(BigDecimal.ONE.negate())));
				this.updateWalletBalance(paymentUpdated);
			}
		}

		payment.setCategory(paymentUpdated.getCategory())
			   .setWallet(paymentUpdated.getWallet())
			   .setDescription(paymentUpdated.getDescription())
			   .setValue(paymentUpdated.getValue())
			   .setInitial(paymentUpdated.getInitial())
			   .setEnd(paymentUpdated.getEnd())
			   .setUpdater("")
			   .setUpdate(LocalDateTime.now());

		return this.repository.save(payment);
	}

	@Transactional
	public void delete(Integer id) {
		Payment payment = this.findById(id);
		try {
			this.updateWalletBalance(payment.setValue(payment.getValue().multiply(BigDecimal.ONE.negate())));
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("");
		}
	}

	public Payment fromDTO(PaymentsDTO paymentsDTO) {
		return new Payment().setId(paymentsDTO.getId())
							.setCategory(this.categoryService.findById(paymentsDTO.getCategory()))
							.setWallet(this.walletService.findById(paymentsDTO.getWallet()))
							.setDescription(CommonFunctions.toUnicode(paymentsDTO.getDescription().toUpperCase()))
							.setValue(paymentsDTO.getValue())
							.setInitial(this.reviewDate(paymentsDTO.getDate()))
							.setEnd(paymentsDTO.getDate());
	}

	private LocalDate reviewDate(LocalDate end) {
		if(end.isBefore(LocalDate.now())) return end;

		return LocalDate.now();
	}

	private void updateWalletBalance(Payment payment) {
		this.walletService.update(payment.getWallet().setBalance(payment.getWallet().getBalance().add(payment.getValue())));
	}

}
