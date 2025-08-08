package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.practicum.util.Constants.EXPLICITY_TIMEOUT;
import static ru.practicum.util.Constants.ScooterColours.BLACK;
import static ru.practicum.util.Constants.ScooterColours.GREY;

import java.time.Duration;

public class OrderFormScooterInfo {
    private final WebDriver driver;
    private final By rentHeader = By.cssSelector(".Order_Header__BZXOb");
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By dropDownMenuDuration = By.cssSelector(".Dropdown-menu");
    private final By colourBlackField = By.cssSelector(".Checkbox_Input__14A2w");
    private final By colourGreyField = By.cssSelector(".Checkbox_Input__14A2w");
    private final By commentField = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN");
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderFormScooterInfo(WebDriver driver) {
        this.driver = driver;
    }

    public void insertDate(String newDate) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(driver -> {
            driver.findElement(rentHeader).getText();
            return !driver.findElement(rentHeader).getText().isEmpty();
        });
        driver.findElement(dateField).sendKeys(newDate);
    }

    public void insertRentalPeriod(String newRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(dropDownMenuDuration)).click();
    }

    public void chooseColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(colourBlackField).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(colourGreyField).click();
        }
    }

    public void insertComment(String newComment) {
        driver.findElement(commentField).sendKeys(newComment);
    }

    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void insertScooterInfo(String newDate, String newRentalPeriod, Enum colour, String newComment) {
        insertDate(newDate);
        insertRentalPeriod(newRentalPeriod);
        chooseColour(colour);
        insertComment(newComment);
        clickOnOrderButton();
    }
}
