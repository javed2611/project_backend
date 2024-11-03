package com.jk.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.jk.entity.Order;

public class ReportSpeicification {

	public static Specification<Order> hasCustomerEmail(String customerEmail) {
		return (root, query, criteriaBuilder) -> {
			if (customerEmail == null || customerEmail.isEmpty()) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("customer").get("email"), customerEmail);
		};
	}

	public static Specification<Order> hasStartDate(LocalDate startDate) {
		return (root, query, criteriaBuilder) -> {
			if (startDate == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate);
		};
	}

	public static Specification<Order> hasEndDate(LocalDate endDate) {
		return (root, query, criteriaBuilder) -> {
			if (endDate == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate);
		};
	}
}
