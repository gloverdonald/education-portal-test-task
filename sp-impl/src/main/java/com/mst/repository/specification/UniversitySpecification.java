package com.mst.repository.specification;

import com.mst.model.UniversityEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class UniversitySpecification {
    public static Specification<UniversityEntity> contextSearch(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            Path<String> name = root.get("universityName");
            Path<String> code = root.get("universityCode");
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(name), "%" + searchValue.toLowerCase() + "%");
            Predicate codePredicate = criteriaBuilder.like(criteriaBuilder.lower(code), "%" + searchValue.toLowerCase() + "%");
            return criteriaBuilder.or(namePredicate, codePredicate);
        };
    }
}