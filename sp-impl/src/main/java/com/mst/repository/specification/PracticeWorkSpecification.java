package com.mst.repository.specification;

import com.mst.model.CourseEntity;
import com.mst.model.PracticeWorkEntity;
import com.mst.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class PracticeWorkSpecification {
    public static Specification<PracticeWorkEntity> inCourseByUser(Long userId, Long courseId) {
        return (root, query, criteriaBuilder) -> {
            Join<PracticeWorkEntity, CourseEntity> courseJoin = root.join("course");
            Join<CourseEntity, UserEntity> userJoin = courseJoin.join("users");
            return criteriaBuilder.and(criteriaBuilder.equal(userJoin.get("id"), userId), criteriaBuilder.equal(courseJoin.get("id"), courseId));
        };
    }
}
