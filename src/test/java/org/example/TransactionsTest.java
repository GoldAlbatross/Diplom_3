package org.example;

import org.example.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_MAIN);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void transactionToPersonalAccount() {
        mainPage.clickPersonalAccount();
        loginPage.login();
        mainPage.clickPersonalAccount();
        assertEquals(Constants.PAGE_PERSONAL_ACCOUNT, driver.getCurrentUrl());
    }

    @Test
    public void transactionFromPersonalAccountToConstructor() {
        mainPage.clickPersonalAccount();
        loginPage.clickLogin();
        mainPage.clickPersonalAccount();
        personalAccountPage.clickLinkConstructor();
        assertEquals(Constants.PAGE_MAIN, driver.getCurrentUrl());
    }

    @Test
    public void transactionFromPersonalAccountToStellarBurgers() {
        mainPage.clickPersonalAccount();
        loginPage.clickLogin();
        mainPage.clickPersonalAccount();
        personalAccountPage.clickLinkStellarBurgers();
        assertEquals(Constants.PAGE_MAIN, driver.getCurrentUrl());
    }

    @Test
    public void ExitFromAccount() {
        mainPage.clickPersonalAccount();
        loginPage.login();
        mainPage.clickPersonalAccount();
        personalAccountPage.clickExitFromAccount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        assertEquals(Constants.PAGE_LOGIN, driver.getCurrentUrl());
    }
}
