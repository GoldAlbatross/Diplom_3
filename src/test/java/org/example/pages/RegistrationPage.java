package org.example.pages;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegistrationPage {

    private final WebDriver driver;

    private static final By INPUT_EMAIL = By.xpath("//div[label[text()='Email']]/input");
    private static final By INPUT_PASSWORD = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private static final By INPUT_NAME = By.xpath("//div[label[text()='Имя']]/input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By LINK_LOGIN = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Войти']");

    private static final String AUTHORIZATION = "Authorization";

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

    @Step("Авторизация пользователя и получение accessToken")
    public String authorizeAndGetToken(String postUrl, String email, String password) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "email", email,
                        "password", password
                ))
                .when()
                .post(postUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Извлекаем accessToken из JSON-ответа
        return response.jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken, String post) {
           given()
                .header(AUTHORIZATION, accessToken)
                .when()
                .delete(post);
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
