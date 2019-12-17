package com.epam.project.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegistrationValidationTest {

    @Test
    public void getInstanceTest() {
        assertNotNull(RegistrationValidation.getInstance());
    }

    @DataProvider(name = "login")
    public Object[][] createLoginData() {
        return new Object[][]{
                {"Joy", true},
                {"<script>123</script>", false},
                {"MeridaMatts", true}};
    }

    @Test(dataProvider = "login")
    public void isCorrectLoginTest(String line, boolean expectedResult) {
        boolean actualResult = RegistrationValidation.getInstance().isCorrectLogin(line);
        assertEquals(actualResult, expectedResult);
    }


    @DataProvider(name = "password")
    public Object[][] createPasswordData() {
        return new Object[][]{
                {"123", false},
                {"12356789", false},
                {"Aa102589", true},
                {"masha147", false}};
    }

    @Test(dataProvider = "password")
    public void isCorrectPasswordTest(String line, boolean expectedResult) {
        boolean actualResult = RegistrationValidation.getInstance().isCorrectPassword(line);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "email")
    public Object[][] createEmailData() {
        return new Object[][]{
                {"123", false},
                {"<script>123</script>", false},
                {"helloWorld@gmail.com", true},
                {"demon123@gmail,com", false}};
    }

    @Test(dataProvider = "email")
    public void isCorrectEmailTest(String line, boolean expectedResult) {
        boolean actualResult = RegistrationValidation.getInstance().isCorrectEmail(line);
        assertEquals(actualResult, expectedResult);
    }
}