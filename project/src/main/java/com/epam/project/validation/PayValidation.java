package com.epam.project.validation;

public class PayValidation {
    public static boolean isEnoughMoney(long currentTime, long rentTime, double cash, double cost){
        return Math.ceil((currentTime - rentTime)/(60 * 60 * 1000)) * cost <= cash;
    }
}
