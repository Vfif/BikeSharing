package com.epam.project.validation;

import java.util.regex.Pattern;

public class ReplenishmentValidation {
    private static ReplenishmentValidation instance;
    private static final String CARD_REG = "\\d{16}";
    private static final String AMOUNT_REG = "\\d{0,5}";

    private ReplenishmentValidation(){}

    public static ReplenishmentValidation getInstance(){
        if(instance == null){
            instance = new ReplenishmentValidation();
        }
        return instance;
    }

    public boolean isCorrectCard(String card) {
        return card != null && Pattern.matches(CARD_REG, card);
    }

    public boolean isCorrectAmount(String amount) {
        return amount != null && Pattern.matches(AMOUNT_REG, amount);
    }

}
