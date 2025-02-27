package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private final WebDriver driver;

    private static final By LINK_CONSTRUCTOR = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX') and .//p[text()='Конструктор']]");
    private static final By LINK_STELLARBURGERS = By.xpath("//*[contains(@class, 'AppHeader_header__logo__2D0X2')]");
    private static final By EXIT_FROM_ACCOUNT = By.xpath("//nav[contains(@class, 'Account_nav__Lgali')]//ul[contains(@class, 'Account_list__3KQQf')]//li//button[contains(text(), 'Выход')]");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("клик по кнопке консруктор")
    public void clickLinkConstructor() {
        driver.findElement(LINK_CONSTRUCTOR).click();
    }

    @Step("клик по кнопке StellarBurgers")
    public void clickLinkStellarBurgers() {
        driver.findElement(LINK_STELLARBURGERS).click();
    }

    @Step("выход с аккаунта")
    public void clickExitFromAccount() {
        waitForElementToBeClickable(EXIT_FROM_ACCOUNT);
        driver.findElement(EXIT_FROM_ACCOUNT).click();
    }

}
