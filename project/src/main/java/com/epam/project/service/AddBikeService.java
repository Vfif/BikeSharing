package com.epam.project.service;

import com.epam.project.validation.AddBikeValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.Map;

import static com.epam.project.type.ParameterName.*;

public class AddBikeService {
    private static Logger Logger = LogManager.getLogger();
    private static AddBikeService instance;

    private AddBikeService(){}

    public static AddBikeService getInstance(){
        if(instance == null){
            instance = new AddBikeService();
        }
        return instance;
    }

    public Map<String, Boolean> checkAddBikeForm(String name, String cost, String description, String address, Part part) {
        boolean incorrectName = !AddBikeValidation.getInstance().isCorrectName(name);
        boolean incorrectCost = !AddBikeValidation.getInstance().isCorrectCost(cost);
        boolean incorrectAddress = !AddBikeValidation.getInstance().isCorrectAddress(address);
        boolean incorrectDescription = !AddBikeValidation.getInstance().isCorrectDescription(description);
        boolean incorrectImage = !AddBikeValidation.getInstance().isCorrectImage(part);


        Map<String, Boolean> map = new HashMap<>();
        map.put(INCORRECT_NAME, incorrectName);
        map.put(INCORRECT_COST, incorrectCost);
        map.put(INCORRECT_ADDRESS, incorrectAddress);
        map.put(INCORRECT_DESCRIPTION, incorrectDescription);
        map.put(INCORRECT_IMAGE, incorrectImage);
        return map;
    }

}




