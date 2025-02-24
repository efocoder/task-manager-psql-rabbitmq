package com.clem.taskmanager.shared;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

public enum StatusEnum {
    ACTIVE(1),
    COMPLETED(2),
    IN_PROGRESS(3),
    DELETED(4),
    ;

    public static final List<StatusEnum> listStatuses = Arrays.asList(StatusEnum.COMPLETED, StatusEnum.IN_PROGRESS, StatusEnum.ACTIVE);
    private final int value;

    StatusEnum(int status) {
        this.value = status;
    }

    // Convert integer to enum
    public static StatusEnum valueToEnum(int currentStatus) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.value == currentStatus) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + currentStatus);
    }

    public static Integer convertToDatabaseColumn(StatusEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }


    public static <T> Specification<T> activeStatus(String statusFieldName) {
        return (root, query, builder) -> builder.equal(root.get(statusFieldName), StatusEnum.ACTIVE);
    }

    public int getStatus() {
        return value;
    }


}
