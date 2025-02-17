package org.example;

import org.example.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private RecoveryPasswordPage recoveryPasswordPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_MAIN);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        registrationPage = new RegistrationPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void transactionToSectionOfBun() {
        assertTrue(mainPage.isBun());
    }

    @Test
    public void transactionToSectionOfSauce() {
        assertTrue(mainPage.isSauce());
    }

    @Test
    public void transactionToSectionOfFelling() {
        assertTrue(mainPage.isFelling());
    }
}
