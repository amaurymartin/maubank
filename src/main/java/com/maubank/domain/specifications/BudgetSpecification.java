package com.maubank.domain.specifications;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.maubank.domain.Budget;

public final class BudgetSpecification implements Specification<Budget> {

	private static final long serialVersionUID = 1L;

	public static Specification<Budget> getSpecification(Integer category, Integer yearIni, Integer yearEnd,
			BigDecimal valueIni, BigDecimal valueEnd, LocalDate initialIni, LocalDate initialEnd, LocalDate endIni, LocalDate endEnd,
			LocalDateTime insertIni, LocalDateTime insertEnd, LocalDateTime updateIni, LocalDateTime updateEnd, String status) {
		return new Specification<Budget>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Budget> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p00 = cb.conjunction();
				Predicate p01 = cb.conjunction();
				Predicate p02 = cb.conjunction();
				Predicate p03 = cb.conjunction();
				Predicate p04 = cb.conjunction();
				Predicate p05 = cb.conjunction();
				Predicate p06 = cb.conjunction();
				Predicate p07 = cb.conjunction();

				if(category != null) p00 = cb.and(cb.equal(root.<Integer>get("category"), category));

				if(yearIni != null) {
					if(yearEnd != null) p01 = cb.and(cb.between(root.<Integer>get("year"), yearIni, yearEnd));
					else p01 = cb.and(cb.equal(root.<Integer>get("year"), yearIni));
				}

				if(valueIni != null) {
					if(valueEnd != null) {
						p02 = cb.and(cb.or(cb.between(root.<BigDecimal>get("january"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("february"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("march"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("april"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("may"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("june"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("july"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("august"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("september"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("october"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("november"), valueIni, valueEnd),
								cb.between(root.<BigDecimal>get("december"), valueIni, valueEnd)));
					} else {
						p02 = cb.or(cb.equal(root.<BigDecimal>get("january"), valueIni),
								cb.equal(root.<BigDecimal>get("february"), valueIni),
								cb.equal(root.<BigDecimal>get("march"), valueIni),
								cb.equal(root.<BigDecimal>get("april"), valueIni),
								cb.equal(root.<BigDecimal>get("may"), valueIni),
								cb.equal(root.<BigDecimal>get("june"), valueIni),
								cb.equal(root.<BigDecimal>get("july"), valueIni),
								cb.equal(root.<BigDecimal>get("august"), valueIni),
								cb.equal(root.<BigDecimal>get("september"), valueIni),
								cb.equal(root.<BigDecimal>get("october"), valueIni),
								cb.equal(root.<BigDecimal>get("november"), valueIni),
								cb.equal(root.<BigDecimal>get("december"), valueIni));
					}
				}

				if(initialIni != null) {
					if(initialEnd != null)
						p03 = cb.and(cb.between(root.<LocalDate>get("initial"),
								LocalDate.of(initialIni.getYear(), Month.JANUARY, 1), LocalDate.of(initialEnd.getYear(), Month.DECEMBER, 31)));
					else p03 = cb.and(cb.equal(root.<LocalDate>get("initial"), LocalDate.of(initialIni.getYear(), Month.JANUARY, 1)));
				}

				if(endIni != null) {
					if(endEnd != null) p04 = cb.and(cb.between(root.<LocalDate>get("end"),
							LocalDate.of(endIni.getYear(), Month.JANUARY, 1), LocalDate.of(endEnd.getYear(), Month.DECEMBER, 31)));
					else p04 = cb.and(cb.equal(root.<LocalDate>get("end"), LocalDate.of(endIni.getYear(), Month.DECEMBER, 31)));
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
	public Predicate toPredicate(Root<Budget> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return null;
	}

}
