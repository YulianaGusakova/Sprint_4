package ru.practicum.pages;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static ru.practicum.util.Constants.EXPLICITY_TIMEOUT;
import static ru.practicum.util.Constants.MakeOrderButton.DOWN_BUTTON;
import static ru.practicum.util.Constants.MakeOrderButton.TOP_BUTTON;

public class MainPage {
    private final WebDriver driver;
    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
    private final By searchField = By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
    private final By statusButton = By.cssSelector(".Header_Link__1TAG7");
    private final By panelFAQ = By.cssSelector(".Home_FAQ__3uVm4");
    private final By topMakeOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By downMakeOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final String questionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel-')][.='%s']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToDownMakeOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downMakeOrderButton));
    }

    public void clickOnTopMakeOrderButton() {
        driver.findElement(topMakeOrderButton).click();
    }

    public void clickOnDownMakeOrderButton() {
        driver.findElement(downMakeOrderButton).click();
    }

    public void clickMakeOrderButton(Enum button) {
        if (button.equals(TOP_BUTTON)) {
            clickOnTopMakeOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownMakeOrderButton();
            clickOnDownMakeOrderButton();
        }
    }

    public void clickOnGoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(goButton));
        driver.findElement(goButton).click();
    }

    public void checkErrorImage() {
        By img = By.cssSelector("img[alt='Not found']");
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(img));
        Assert.assertTrue(driver.findElement(img).isDisplayed());
    }

    public void sendOrderNumber() {
        driver.findElement(searchField).sendKeys("123");
    }

    public void clickOnStatusButton() {
        driver.findElement(statusButton).click();
    }

    public void checkAnswerText(String expectedText) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(format(answerLocator, expectedText))));
        String result = driver.findElement(By.xpath(format(answerLocator, expectedText))).getText();
        assertEquals("Текст ответа содержит ошибку", expectedText, result);
    }

    public void clickOnQuestionButton(int index) {
        driver.findElement(By.id(String.format(questionLocator, index))).click();
    }

    public void scrollPageDownToFAQ() {
        WebElement tableFAQ = driver.findElement(panelFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
    }
}
