package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на кнопку войти")
    public LoginPage clickSignInButton() {
        signInButton.click();
        return new LoginPage(driver);

    }
}
