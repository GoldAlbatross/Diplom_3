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

    @Before
    public void setUp() {
        driver = Browser.getWebDriver("chrome");
        driver.get(Constants.PAGE_MAIN);
        mainPage = new MainPage(driver);
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
