package com.maubank.domain.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.maubank.domain.Category;
import com.maubank.utils.CommonFunctions;

public final class CategorySpecification implements Specification<Category> {

	private static final long serialVersionUID = 1L;

	public static Specification<Category> getSpecification(Integer user, String description, String group,
			LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd, String status) {
		return new Specification<Category>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p00 = cb.conjunction();
				Predicate p01 = cb.conjunction();
				Predicate p02 = cb.conjunction();
				Predicate p03 = cb.conjunction();
				Predicate p04 = cb.conjunction();
				Predicate p05 = cb.conjunction();
				Predicate p06 = cb.conjunction();
				Predicate p07 = cb.conjunction();

				if(user != null) p00 = cb.and(cb.equal(root.<Integer>get("user"), user));

				if(description != null)
					p01 = cb.and(cb.like(root.<String>get("description"), "%" + CommonFunctions.toUnicode(description.toUpperCase()) + "%"));

				if(group != null) p02 = cb.and(cb.equal(root.<String>get("group"), group.toUpperCase()));

				if(initialIni != null) {
					if(initialEnd != null) p03 = cb.and(cb.between(root.<LocalDate>get("initial"), initialIni, initialEnd));
					else p03 = cb.and(cb.equal(root.<LocalDate>get("initial"), initialIni));
				}

				if(endIni != null) {
					if(endEnd != null) p04 = cb.and(cb.between(root.<LocalDate>get("end"), endIni, endEnd));
					else p04 = cb.and(cb.equal(root.<LocalDate>get("end"), endIni));
				}

				if(insertIni != null) {
					if(insertEnd != null) p05 = cb.and(cb.between(root.<LocalDateTime>get("insert"), insertIni, insertEnd));
					else p05 = cb.and(cb.equal(root.<LocalDateTime>get("insert"), insertIni));
				}

				if(updateIni != null) {
					if(updateEnd != null) p06 = cb.and(cb.between(root.<LocalDateTime>get("update"), updateIni, updateEnd));
					else p06 = cb.and(cb.equal(root.<LocalDate>get("update"), updateIni));
				}

				if(status != null) p07 = cb.and(cb.equal(root.<String>get("status"), status.toUpperCase()));

				return cb.and(p00, p01, p02, p03, p04, p05, p06, p07);
			}
		};
	}

	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return null;
	}

}
