package com.mst.repository.specification;

import com.mst.model.AcademyGroupEntity;
import com.mst.model.UniversityYearEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class AcademyGroupSpecification {
    public static Specification<AcademyGroupEntity> byAcademyYear(Long academyYearId) {
        return (root, query, criteriaBuilder) -> {
            Join<AcademyGroupEntity, UniversityYearEntity> universityYearEntityJoin = root.join("universityYear");
            return criteriaBuilder.equal(universityYearEntityJoin.get("id"), academyYearId);
        };
    }

    public static Specification<AcademyGroupEntity> contextSearch(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            Path<String> name = root.get("groupName");
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(name), "%" + searchValue.toLowerCase() + "%");
            return criteriaBuilder.or(namePredicate);
        };
    }
}