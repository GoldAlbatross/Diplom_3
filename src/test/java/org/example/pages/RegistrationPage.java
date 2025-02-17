package org.example.pages;

import io.qameta.allure.Step;
import org.example.Browser;
import org.example.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    private static final By INPUT_EMAIL = By.xpath("//div[label[text()='Email']]/input");
    private static final By INPUT_PASSWORD = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private static final By INPUT_NAME = By.xpath("//div[label[text()='Имя']]/input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By LINK_LOGIN = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Войти']");

    public RegistrationPage(WebDriver driver) {
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

    @Step("Ввод email")
    public void enterEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    @Step("Ввод password")
    public void enterPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }

    @Step("Ввод name")
    public void enterName(String name) {
        driver.findElement(INPUT_NAME).sendKeys(name);
    }

    @Step("клик по нопки регистрации")
    public void clickRegistrationButton() {
        waitForElementToBeClickable(REGISTER_BUTTON);
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("клик по кнопке войти")
    public void clickLogin() {
        driver.findElement(LINK_LOGIN).click();
    }

    @Step("регистрация пользователя")
    public void registration(String password) {
        enterName(Constants.NAME_FOR_REGISTRATION);
        enterEmail(Constants.EMAIL_FOR_REGISTRATION);
        enterPassword(password);
        clickRegistrationButton();
    }
}
