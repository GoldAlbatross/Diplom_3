package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    private static final By BTN_LOGIN_IN_ACCOUNT = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By BTN_CREATE_ORDER = By.xpath("//button[text()='Оформить заказ']");
    private static final By SELECTION_OF_BUN = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and span[text()='Булки']]");
    private static final By SELECTION_OF_BUN_ACTIVE = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc') and span[text()='Булки']]");
    private static final By SELECTION_OF_SAUCES = By.xpath("//div[span[text()='Соусы']][not(contains(@class, 'tab_tab_type_current__'))]");
    private static final By SELECTION_OF_SAUCES_ACTIVE = By.xpath("//div[contains(@class, 'tab_tab_type_current__')][span[text()='Соусы']]");
    private static final By SELECTION_OF_FELLING = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and span[text()='Начинки']]");
    private static final By SELECTION_OF_FELLING_ACTIVE = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc') and span[text()='Начинки']]");
    private static final By LINK_PERSONAL_ACCOUNT = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX') and contains(., 'Личный Кабинет')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Клик по кнопке войти в аккаунт")
    public void clickLoginInAccount() {
        waitForElementToBeVisible(BTN_LOGIN_IN_ACCOUNT);
        driver.findElement(BTN_LOGIN_IN_ACCOUNT).click();
    }

    @Step("клик по кнопке личный кабинет")
    public void clickPersonalAccount() {
        waitForElementToBeVisible(LINK_PERSONAL_ACCOUNT);
        driver.findElement(LINK_PERSONAL_ACCOUNT).click();
    }

    @Step("клик по секции булки")
    public void clickSelectionOfBun() {
        waitForElementToBeClickable(SELECTION_OF_BUN);
        driver.findElement(SELECTION_OF_BUN).click();
    }

    @Step("клик по секции соусы")
    public void clickSelectionOfSauce() {
        waitForElementToBeClickable(SELECTION_OF_SAUCES);
        driver.findElement(SELECTION_OF_SAUCES).click();
    }

    @Step("клик по секции начинки")
    public void clickSelectionOfFelling() {
        waitForElementToBeClickable(SELECTION_OF_FELLING);
        driver.findElement(SELECTION_OF_FELLING).click();
    }

    @Step("Проверка на переход по секции булки")
    public boolean isBun() {
        try {
            clickSelectionOfSauce();
            clickSelectionOfBun();
            waitForElementToBeVisible(SELECTION_OF_BUN_ACTIVE);
            return driver.findElement(SELECTION_OF_BUN_ACTIVE).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка на переход по секции соусы")
    public boolean isSauce() {
        try {
            clickSelectionOfSauce();
            waitForElementToBeVisible(SELECTION_OF_SAUCES_ACTIVE);
            return driver.findElement(SELECTION_OF_SAUCES_ACTIVE).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка на переход по секции начинка")
    public boolean isFelling() {
        try {
            clickSelectionOfFelling();
            waitForElementToBeVisible(SELECTION_OF_FELLING_ACTIVE);
            return driver.findElement(SELECTION_OF_FELLING_ACTIVE).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка на корректный вход")
    public boolean isEnterCorrect() {
        try {
            waitForElementToBeClickable(BTN_CREATE_ORDER);
            WebElement btn = driver.findElement(BTN_CREATE_ORDER);
            return btn.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}
