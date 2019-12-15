package com.epam.project.validation;

import javax.servlet.http.Part;
import java.util.regex.Pattern;

public class AddBikeValidation {
    private static final String NAME_REG = "[A-Za-zА-Яа-я\\d\\s-_]{1,45}";
    private static final String COST_REG = "\\d{1,3}";
    private static final String ADDRESS_REG = "[A-Za-zА-Яа-я\\d\\s.,]{1,45}";
    private static final String DESCRIPTION_REG = "[A-Za-zА-Яа-я\\d\\s.,]{1,200}";
    private static final String IMAGE_REG = ".*\\.(jpeg|png|jpg)$";

    private static AddBikeValidation instance;

    private AddBikeValidation() {
    }

    public static AddBikeValidation getInstance() {
        if (instance == null) {
            instance = new AddBikeValidation();
        }
        return instance;
    }

    public boolean isCorrectName(String name) {
        return name != null && Pattern.matches(NAME_REG, name);
    }

    public boolean isCorrectCost(String cost) {
        return cost != null && Pattern.matches(COST_REG, cost);
    }

    public boolean isCorrectDescription(String description) {
        return description != null && Pattern.matches(DESCRIPTION_REG, description);
    }

    public boolean isCorrectAddress(String address) {
        return address != null && Pattern.matches(ADDRESS_REG, address);
    }

    public boolean isCorrectImage(Part part) {
        return part != null && Pattern.matches(IMAGE_REG, part.getSubmittedFileName());
    }
}

