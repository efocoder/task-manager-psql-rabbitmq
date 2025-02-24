package com.clem.taskmanager.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Shared {

    private Shared() {
        throw new UnsupportedOperationException("Utility class");
    }


    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date); // Format as "yyyy-MM-dd HH:mm:ss"
    }
}
