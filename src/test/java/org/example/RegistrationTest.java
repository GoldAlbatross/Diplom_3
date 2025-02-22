package org.example;

import io.restassured.RestAssured;
import org.example.pages.LoginPage;
import org.example.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private String accessToken;


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_REGISTRATION);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkRegistration() {
        registrationPage.registration(Constants.CORRECT_PASSWORD_FOR_REGISTRATION);
        assertTrue(loginPage.isRegistrationCorrect());
        accessToken = registrationPage.authorizeAndGetToken(Constants.POST_FOR_AUTHORIZATION, Constants.EMAIL_FOR_REGISTRATION, Constants.CORRECT_PASSWORD_FOR_REGISTRATION);
        registrationPage.deleteUser(accessToken, Constants.POST_FOR_DELETE);
    }

    @Test
    public void checkRegistrationWithShortPassword() {
        registrationPage.registration(Constants.INCORRECT_PASSWORD_FOR_REGISTRATION);
        assertFalse(loginPage.isRegistrationCorrect());
    }

}

