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
    private static final By SELECTION_OF_BUN = By.xpath("//div[@style='display: flex;']/div[1]/span[text()='Булки']");
    private static final By SELECTION_OF_SAUCES = By.xpath("//div[@style='display: flex;']/div[2]/span[text()='Соусы']");
    private static final By SELECTION_OF_FELLING = By.xpath("//div[@style='display: flex;']/div[3]/span[text()='Начинки']");
    private static final By TEXT_BUN = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Булки']");
    private static final By TEXT_SAUCE = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Соусы']");
    private static final By TEXT_FELLING = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Начинки']\n");
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
            waitForElementToBeVisible(TEXT_BUN);
            return driver.findElement(TEXT_BUN).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка на переход по секции соусы")
    public boolean isSauce() {
        try {
            clickSelectionOfSauce();
            waitForElementToBeVisible(TEXT_SAUCE);
            return driver.findElement(TEXT_SAUCE).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка на переход по секции начинка")
    public boolean isFelling() {
        try {
            clickSelectionOfFelling();
            waitForElementToBeVisible(TEXT_FELLING);
            return driver.findElement(TEXT_FELLING).isDisplayed();
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
