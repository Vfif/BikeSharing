package com.epam.project.validation;

public class PayValidation {
    private static PayValidation instance;
    private PayValidation(){}

    public static PayValidation getInstance(){
        if(instance == null){
            instance = new PayValidation();
        }
        return instance;
    }

    public boolean isEnoughMoney(long currentTime, long rentTime, double cash, double cost){
        return Math.ceil(((currentTime - rentTime)* cost )/(60 * 60 * 1000) )<= cash;
    }
}
