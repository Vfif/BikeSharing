package com.epam.project.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginValidationTest {

    @Test
    public void getInstance() {
        assertNotNull(LoginValidation.getInstance());
    }

    @DataProvider(name = "xssAttack")
    public Object[][] createData() {
        return new Object[][]{
                {"123", false},
                {"<script>123</script>", true},
                {"<script type=\"text/javascript\"> 123 </script>", true},
                {"<script>123<script>", false},
                {"<script>123</script", false}};
    }

    @Test(dataProvider = "xssAttack")
    public void isXssAttackTest(String line, boolean expectedResult) {
        boolean actualResult = LoginValidation.getInstance().isXssAttack(line);
        assertEquals(actualResult, expectedResult);
    }
}