package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.util.Constants.EXPLICITY_TIMEOUT;

public class OrderConfirmation {
    private final WebDriver driver;
    private final By confirmationHeader = By.cssSelector(".Order_ModalHeader__3FDaJ");
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private final By successfulOrderHeader = By.xpath(".//div[text()='Заказ оформлен']");
    private final String expectedHeader = "Заказ оформлен";

    public OrderConfirmation(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(driver -> {
            driver.findElement(confirmationHeader).getText();
            return !driver.findElement(confirmationHeader).getText().isEmpty();
        });
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    public void getSuccessfulOrderHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(driver -> {
            driver.findElement(successfulOrderHeader).getText();
            return !driver.findElement(successfulOrderHeader).getText().isEmpty();
        });
        String actualText = driver.findElement(successfulOrderHeader).getText();
        Assert.assertTrue("Заголовок содержит ожидаемый текст", actualText.contains(expectedHeader));

    }
}

