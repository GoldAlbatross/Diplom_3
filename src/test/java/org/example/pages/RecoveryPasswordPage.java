package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {

    private final WebDriver driver;

    private static final By BTN_LOGIN = By.xpath("//a[text()='Войти']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("клик по кнопке вход")
    public void clickBtnLogin() {
        driver.findElement(BTN_LOGIN).click();
    }
}
