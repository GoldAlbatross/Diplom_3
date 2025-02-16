package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;


    @Before
    public void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--whitelisted-ips=\"\"");
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Feature("Регистрация пользователя")
    @Story("Пользователь успешно регистрируется с правильными данными")
    @Description("Тестирует регистрацию с валидными данными: email, пароль, имя")
    public void whenValidRegistration_thenSuccess() {
        registrationPage.fillRegistrationForm("test@mail.com", "123456", "Test User");
        registrationPage.submitRegistration();
        assertTrue("Registration should be successful", registrationPage.isRegistrationSuccessful());
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

