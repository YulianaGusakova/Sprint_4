package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.practicum.util.Constants.EXPLICITY_TIMEOUT;

import java.time.Duration;

public class OrderFormUserInfo {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.cssSelector(".select-search__input");
    private final String nameMetroStation = ".//button[@value='%s']";
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public OrderFormUserInfo(WebDriver driver) {
        this.driver = driver;
    }

    public void insertName(String newName) {
        driver.findElement(nameField).sendKeys(newName);
    }

    public void insertSurname(String newSurname) {
        driver.findElement(surnameField).sendKeys(newSurname);
    }

    public void insertAddress(String newAddress) {
        driver.findElement(addressField).sendKeys(newAddress);
    }

    public void chooseMetroStation(int stationNumber) {
        driver.findElement(metroStationField).click();
        By newMetroStation = By.xpath(String.format(nameMetroStation, stationNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newMetroStation));
        driver.findElement(newMetroStation).click();
    }

    public void insertPhoneNumber(String newPhoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(phoneNumber));
        driver.findElement(phoneNumber).sendKeys(newPhoneNumber);
    }

    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }
}
