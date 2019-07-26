package com.maubank.domain.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.maubank.domain.User;
import com.maubank.utils.CommonFunctions;

public final class UserSpecification implements Specification<User> {

	private static final long serialVersionUID = 1L;

	public static Specification<User> getSpecification(String username, String email, String name, String surname, String cpf,
			LocalDate birthIni, LocalDate birthEnd, LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd, String status) {
		return new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p00 = cb.conjunction();
				Predicate p01 = cb.conjunction();
				Predicate p02 = cb.conjunction();
				Predicate p03 = cb.conjunction();
				Predicate p04 = cb.conjunction();
				Predicate p05 = cb.conjunction();
				Predicate p06 = cb.conjunction();
				Predicate p07 = cb.conjunction();
				Predicate p08 = cb.conjunction();
				Predicate p09 = cb.conjunction();
				Predicate p10 = cb.conjunction();

				if(username != null)
					p00 = cb.and(cb.like(root.<String>get("username"), "%" + CommonFunctions.toUnicode(username.toLowerCase()) + "%"));

				if(email != null) p01 = cb.and(cb.like(root.<String>get("email"), "%" + CommonFunctions.toUnicode(email.toLowerCase()) + "%"));

				if(name != null) p02 = cb.and(cb.like(root.<String>get("name"), "%" + CommonFunctions.toUnicode(name.toUpperCase() + "%")));

				if(surname != null) p03 = cb.and(cb.like(root.<String>get("surname"), "%" + CommonFunctions.toUnicode(surname.toUpperCase() + "%")));

				if(cpf != null) p04 = cb.and(cb.like(root.<String>get("cpf"), "%" + CommonFunctions.toUnicode(cpf.replaceAll("\\D", "")) + "%"));

				if(birthIni != null) {
					if(birthEnd != null) p05 = cb.and(cb.between(root.<LocalDate>get("birth"), birthIni, birthEnd));
					else p05 = cb.and(cb.equal(root.<LocalDate>get("birth"), birthIni));
				}

				if(initialIni != null) {
					if(initialEnd != null) p06 = cb.and(cb.between(root.<LocalDate>get("initial"), initialIni, initialEnd));
					else p06 = cb.and(cb.equal(root.<LocalDate>get("initial"), initialIni));
				}

				if(endIni != null) {
					if(endEnd != null) p07 = cb.and(cb.between(root.<LocalDate>get("end"), endIni, endEnd));
					else p07 = cb.and(cb.equal(root.<LocalDate>get("end"), endIni));
				}

				if(insertIni != null) {
					if(insertEnd != null) p08 = cb.and(cb.between(root.<LocalDateTime>get("insert"), insertIni, insertEnd));
					else p08 = cb.and(cb.equal(root.<LocalDateTime>get("insert"), insertIni));
				}

				if(updateIni != null) {
					if(updateEnd != null) p09 = cb.and(cb.between(root.<LocalDateTime>get("update"), updateIni, updateEnd));
					else p09 = cb.and(cb.equal(root.<LocalDate>get("update"), updateIni));
				}

				if(status != null) p10 = cb.and(cb.equal(root.<String>get("status"), status));

				return cb.and(p00, p01, p02, p03, p04, p05, p06, p07, p08, p09, p10);
			}
		};
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return null;
	}

}
