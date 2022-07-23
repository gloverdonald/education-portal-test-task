package com.mst.repository.specification;

import com.mst.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class CourseSpecification {
    public static Specification<CourseEntity> byUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            Join<CourseEntity, UniversityEntity> courseJoin = root.join("university");
            Join<UniversityEntity, UniversityYearEntity> universityJoin = courseJoin.join("universityYears");
            Join<UniversityYearEntity, AcademyGroupEntity> universityYearsJoin = universityJoin.join("academyGroups");
            Join<AcademyGroupEntity, UserEntity> academyGroupsJoin = universityYearsJoin.join("users");
            return criteriaBuilder.equal(academyGroupsJoin.get("id"), userId);
        };
    }
}