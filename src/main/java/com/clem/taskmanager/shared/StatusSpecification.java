package com.clem.taskmanager.shared;

import org.springframework.data.jpa.domain.Specification;

public class StatusSpecification {
    public static <T> Specification<T> activeStatus(String statusFieldName) {
        return (root, query, builder) -> builder.equal(root.get(statusFieldName), StatusEnum.ACTIVE);
    }
}
