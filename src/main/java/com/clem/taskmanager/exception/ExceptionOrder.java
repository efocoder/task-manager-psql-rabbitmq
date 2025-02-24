package com.clem.taskmanager.exception;

import org.springframework.core.Ordered;

public class ExceptionOrder {
    public static final int VALIDATION = Ordered.HIGHEST_PRECEDENCE;
    public static final int NOT_FOUND = Ordered.HIGHEST_PRECEDENCE + 100;
    public static final int CUSTOM_BUSINESS = Ordered.HIGHEST_PRECEDENCE + 200;
    public static final int SECURITY = Ordered.HIGHEST_PRECEDENCE + 300;
    public static final int GLOBAL = Ordered.LOWEST_PRECEDENCE;
}
