package edu.ccrm.utils;

import java.util.regex.Pattern;


public final class inputValidator {


    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    private inputValidator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }


    public static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }
}