package org.example;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
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
    private MainPage mainPage;
    private LoginPage loginPage;


    @Before
    public void setUp() {
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_REGISTRATION);
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
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
    }

    @Test
    public void checkRegistrationWithShortPassword() {
        registrationPage.registration(Constants.INCORRECT_PASSWORD_FOR_REGISTRATION);
        assertFalse(loginPage.isRegistrationCorrect());
    }

}

