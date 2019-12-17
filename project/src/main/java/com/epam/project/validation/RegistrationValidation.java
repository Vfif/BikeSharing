package com.epam.project.validation;

import java.util.regex.Pattern;

public class RegistrationValidation {
    private static final String LOGIN_REG = "[A-Za-zА-Яа-я\\d\\-\\_]{1,45}";
    private static final String PASSWORD_REG = "^(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\\d)[A-Za-zА-Яа-я\\d]{8,45}$";
    private static final String EMAIL_REG = "^[A-Za-z\\d_]{6,}@[a-z]+\\.[a-z]{2,4}$";
    private static RegistrationValidation instance;

    private RegistrationValidation() {
    }

    public static RegistrationValidation getInstance() {
        if (instance == null) {
            instance = new RegistrationValidation();
        }
        return instance;
    }

    public boolean isCorrectLogin(String login) {
        return login != null && Pattern.matches(LOGIN_REG, login);
    }

    public boolean isCorrectPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REG, password);//at least one letter uppercase and one lowercase and one digit
    }

    public boolean isCorrectEmail(String email) {
        return email != null && email.length() < 46 && Pattern.matches(EMAIL_REG, email);
    }
}
