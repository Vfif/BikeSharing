package com.epam.project.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddBikeValidationTest {

    @Test
    public void getInstanceTest() {
        assertNotNull(AddBikeValidation.getInstance());
    }

    @DataProvider(name = "name")
    public Object[][] createNameData() {
        return new Object[][]{
                {"Joy", true},
                {"<script>123</script>", false},
                {"MeridaMatts", true}};
    }

    @Test(dataProvider = "name")
    public void isCorrectNameTest(String line, boolean expectedResult) {
        boolean actualResult = AddBikeValidation.getInstance().isCorrectName(line);
        assertEquals(actualResult, expectedResult);
    }


    @DataProvider(name = "cost")
    public Object[][] createCostData() {
        return new Object[][]{
                {"123", true},
                {"12356789", false},
                {"Aa102589", false},
                {"masha147", false}};
    }

    @Test(dataProvider = "cost")
    public void isCorrectPasswordTest(String line, boolean expectedResult) {
        boolean actualResult = AddBikeValidation.getInstance().isCorrectCost(line);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "description")
    public Object[][] creatDescriptionData() {
        return new Object[][]{
                {"123", true},
                {"<script>123</script>", false},
                {"pretty bike", true}};
    }

    @Test(dataProvider = "description")
    public void isCorrectDescriptionTest(String line, boolean expectedResult) {
        boolean actualResult = AddBikeValidation.getInstance().isCorrectDescription(line);
        assertEquals(actualResult, expectedResult);
    }

}