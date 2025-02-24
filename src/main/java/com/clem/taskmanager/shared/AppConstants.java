package com.clem.taskmanager.shared;

public class AppConstants {
    public static final String APP_NAME = "TaskManager";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private AppConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

}
