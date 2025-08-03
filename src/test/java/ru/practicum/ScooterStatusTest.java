package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.practicum.pages.MainPage;

public class ScooterStatusTest {
    @Rule
    public DriverFactory factory = new DriverFactory();
    private By question;
    private By answer;

    @Test
    public void testNonExistingOrderNotFound() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver, question, answer);
        mainPage.clickOnStatusButton();
        mainPage.sendOrderNumber();
        mainPage.clickOnGoButton();
        mainPage.CheckErrorImage();
    }
}
