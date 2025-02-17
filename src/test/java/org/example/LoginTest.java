package org.example;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RecoveryPasswordPage;
import org.example.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private RecoveryPasswordPage recoveryPasswordPage;

    @Before
    public void setUp() {
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_MAIN);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginInViaBtnLogin() {
        mainPage.clickLoginInAccount();
        loginPage.login();
        assertTrue(mainPage.isEnterCorrect());
    }

    @Test
    public void loginViaPersonalAccount() {
        mainPage.clickPersonalAccount();
        loginPage.login();
        assertTrue(mainPage.isEnterCorrect());
    }

    @Test
    public void loginInViaRegistrationForm() {
        driver.get(Constants.PAGE_REGISTRATION);
        registrationPage.clickLogin();
        loginPage.login();
        assertTrue(loginPage.isRegistrationCorrect());
    }

    @Test
    public void loginViaPasswordRecoveryForm() {
        driver.get(Constants.PAGE_LOGIN);
        loginPage.clickRecoveryPassword();
        recoveryPasswordPage.clickBtnLogin();
        loginPage.login();
        assertTrue(mainPage.isEnterCorrect());
    }
}
