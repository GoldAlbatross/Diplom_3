package org.example.pages;

import io.qameta.allure.Step;
import org.example.Browser;
import org.example.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private static final By INPUT_EMAIL = By.xpath("//div[label[text()='Email']]/input");
    private static final By BTN_LOGIN = By.xpath("//button[contains(@class, 'button_button__33qZ0') and normalize-space(text())='Войти']");
    private static final By INPUT_PASSWORD = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private static final By TEXT_ENTER = By.xpath("//h2[contains(text(), 'Вход')]");
    private static final By LINK_CONSTRUCTOR = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX') and .//p[text()='Конструктор']]");
    private static final By BTN_RECOVERY_PASSWORD = By.xpath("//a[contains(@class, 'Auth_link__') and text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
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

    public void enterEmail() {
        driver.findElement(INPUT_EMAIL).sendKeys(Constants.EMAIL_FOR_LOGIN);
    }

    public void enterPassword() {
        driver.findElement(INPUT_PASSWORD).sendKeys(Constants.PASSWORD_FOR_LOGIN);
    }

    public void clickLogin() {
        waitForElementToBeClickable(BTN_LOGIN);
        driver.findElement(BTN_LOGIN).click();
    }

    @Step("Клик по кнопке восстановления пороля")
    public void clickRecoveryPassword() {
        driver.findElement(BTN_RECOVERY_PASSWORD).click();
    }

    @Step("Проверка на корректную регистрацию")
    public boolean isRegistrationCorrect() {
        try {
            waitForElementToBeClickable(TEXT_ENTER);
            WebElement enter = driver.findElement(TEXT_ENTER);
            return enter.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Вход в аккаунт")
    public void login() {
        enterEmail();
        enterPassword();
        clickLogin();
    }
}
