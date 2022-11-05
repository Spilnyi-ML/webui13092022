package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class TShirtsPage extends BaseView{
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    private List<WebElement> sizesList;

    @Step("Выбираем размер")
    public TShirtsPage selectSize(String size) {
        webDriverWait.until(d -> sizesList.size() > 0);
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//div[contains(@class, 'slider')]//a[1]")
    private WebElement leftPriceSliderElement;

    @Step("Сдвигаем слайдер цены вправо")
    public TShirtsPage moveLeftPriceSliderElement(int pixelCount) {
        actions.clickAndHold(leftPriceSliderElement)
                .moveByOffset(pixelCount, 0)
                .perform();
        return this;
    }

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> dressesList;

    private static final String addToCartButtonXpathLocator = "//span[.='Add to cart']";
    @FindBy(xpath = addToCartButtonXpathLocator)
    private WebElement addToCartButton;

    @Step("Добавляем товар в корзину по имени")
    public SuccessBlock addToCartByName(String tShirtName) throws InterruptedException {
        actions.moveToElement(dressesList.stream().filter(d -> d.getText().contains(tShirtName)).findFirst().get())
                        .perform();

        dressesList.stream().filter(d -> d.getText().contains(tShirtName)).findFirst().get().findElement(
                By.xpath(addToCartButtonXpathLocator)).click();
        return new SuccessBlock(driver);
    }
}
