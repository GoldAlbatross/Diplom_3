package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class RegistrationPage {

    private WebDriver driver;

    // Локаторы
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By nameField = By.name("name");
    private By submitButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");

    // Конструктор
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Шаги для взаимодействия с полями формы
    @Step("Заполнение формы регистрации с email {0}, паролем {1} и именем {2}")
    public void fillRegistrationForm(String email, String password, String name) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Нажатие кнопки регистрации")
    public void submitRegistration() {
        driver.findElement(submitButton).click();
    }

    @Step("Проверка успешной регистрации")
    public boolean isRegistrationSuccessful() {
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Вы зарегистрированы')]"));
        return successMessage.isDisplayed();
    }

    @Step("Проверка ошибки с паролем меньше 6 символов")
    public boolean isPasswordErrorDisplayed() {
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Пароль должен быть не менее 6 символов')]"));
        return errorMessage.isDisplayed();
    }
}

