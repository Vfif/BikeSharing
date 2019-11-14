package com.epam.project.validation;

import java.util.regex.Pattern;

public class RegistrationValidation {
    private static final String PASSWORD_REG = "^(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String EMAIL_REG = "^\\w{6,}@\\w+\\.\\p{Lower}{2,4}$";

    public static boolean isCorrectPassword(String password){
        return Pattern.matches(PASSWORD_REG, password);
        //at least one letter uppercase and one lowercase and one digit
    }

    public static boolean isCorrectEmail(String email) {
        return Pattern.matches(EMAIL_REG, email);
    }
}
