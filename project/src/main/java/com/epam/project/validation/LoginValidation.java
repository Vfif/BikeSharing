package com.epam.project.validation;

import java.util.regex.Pattern;

public class LoginValidation {
    private static LoginValidation instance;
    private static final String XSS_REG = "<script.*?>.*?</script>";
    private LoginValidation(){}

    public static LoginValidation getInstance(){
        if(instance == null){
            instance = new LoginValidation();
        }
        return instance;
    }

    public boolean isXssAttack(String line){
        return line != null && Pattern.matches(XSS_REG,line);
    }
}
