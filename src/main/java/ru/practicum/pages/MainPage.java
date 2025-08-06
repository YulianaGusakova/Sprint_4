package ru.practicum.pages;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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
    private final By questionLocator;
    private final By answerLocator;
    private final By topMakeOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By downMakeOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private String expectedText;

    private static final By QUESTION_PAYMENT = By.id("accordion__heading-0");
    private static final By QUESTION_SCOOTER_QUANTITY = By.id("accordion__heading-1");
    private static final By QUESTION_RENT_DURATION = By.id("accordion__heading-2");
    private static final By QUESTION_TODAY_ORDER = By.id("accordion__heading-3");
    private static final By QUESTION_DURATION_CHANGE = By.id("accordion__heading-4");
    private static final By QUESTION_SCOOTER_CHARGER = By.id("accordion__heading-5");
    private static final By QUESTION_ORDER_CANCEL = By.id("accordion__heading-6");
    private static final By QUESTION_OUTSIDE_MKAD_ORDER = By.id("accordion__heading-7");

    private static final By ANSWER_PAYMENT = By.id("accordion__panel-0");
    private static final By ANSWER_SCOOTER_QUANTITY = By.id("accordion__panel-1");
    private static final By ANSWER_RENT_DURATION = By.id("accordion__panel-2");
    private static final By ANSWER_TODAY_ORDER = By.id("accordion__panel-3");
    private static final By ANSWER_DURATION_CHANGE = By.id("accordion__panel-4");
    private static final By ANSWER_SCOOTER_CHARGER = By.id("accordion__panel-5");
    private static final By ANSWER_ORDER_CANCEL = By.id("accordion__panel-6");
    private static final By ANSWER_OUTSIDE_MKAD_ORDER = By.id("accordion__panel-7");

    private static final By ITEM_ANSWER_PAYMENT = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-0']");
    private static final By ITEM_ANSWER_SCOOTER_QUANTITY = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-1']");
    private static final By ITEM_ANSWER_RENT_DURATION = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-2']");
    private static final By ITEM_ANSWER_TODAY_ORDER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-3']");
    private static final By ITEM_ANSWER_DURATION_CHANGE = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-4']");
    private static final By ITEM_ANSWER_SCOOTER_CHARGER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-5']");
    private static final By ITEM_ANSWER_ORDER_CANCEL = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-6']");
    private static final By ITEM_ANSWER_OUTSIDE_MKAD_ORDER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-7']");

    private static final String TEXT_ANSWER_PAYMENT = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String TEXT_ANSWER_SCOOTER_QUANTITY = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String TEXT_ANSWER_RENT_DURATION = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
            "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String TEXT_ANSWER_TODAY_ORDER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String TEXT_ANSWER_DURATION_CHANGE = "Пока что нет! Но если что-то срочное — " +
            "всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String TEXT_ANSWER_SCOOTER_CHARGER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
            "даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String TEXT_ANSWER_ORDER_CANCEL = "Да, пока самокат не привезли. Штрафа не будет, " +
            "объяснительной записки тоже не попросим. Все же свои.";
    private static final String TEXT_ANSWER_OUTSIDE_MKAD_ORDER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    public MainPage(WebDriver driver, By questionLocator, By answerLocator, String expectedText) {
        this.driver = driver;
        this.questionLocator = questionLocator;
        this.answerLocator = answerLocator;
        this.expectedText = expectedText;
    }

    public static List<Object[]> getFAQParameters() {
        return Arrays.asList(
                new Object[]{
                        QUESTION_PAYMENT,
                        ANSWER_PAYMENT,
                        ITEM_ANSWER_PAYMENT,
                        TEXT_ANSWER_PAYMENT
                },
                new Object[]{
                        QUESTION_SCOOTER_QUANTITY,
                        ANSWER_SCOOTER_QUANTITY,
                        ITEM_ANSWER_SCOOTER_QUANTITY,
                        TEXT_ANSWER_SCOOTER_QUANTITY
                },
                new Object[]{
                        QUESTION_RENT_DURATION,
                        ANSWER_RENT_DURATION,
                        ITEM_ANSWER_RENT_DURATION,
                        TEXT_ANSWER_RENT_DURATION
                },
                new Object[]{
                        QUESTION_TODAY_ORDER,
                        ANSWER_TODAY_ORDER,
                        ITEM_ANSWER_TODAY_ORDER,
                        TEXT_ANSWER_TODAY_ORDER
                },
                new Object[]{
                        QUESTION_DURATION_CHANGE,
                        ANSWER_DURATION_CHANGE,
                        ITEM_ANSWER_DURATION_CHANGE,
                        TEXT_ANSWER_DURATION_CHANGE
                },
                new Object[]{
                        QUESTION_SCOOTER_CHARGER,
                        ANSWER_SCOOTER_CHARGER,
                        ITEM_ANSWER_SCOOTER_CHARGER,
                        TEXT_ANSWER_SCOOTER_CHARGER
                },
                new Object[]{
                        QUESTION_ORDER_CANCEL,
                        ANSWER_ORDER_CANCEL,
                        ITEM_ANSWER_ORDER_CANCEL,
                        TEXT_ANSWER_ORDER_CANCEL
                },
                new Object[]{
                        QUESTION_OUTSIDE_MKAD_ORDER,
                        ANSWER_OUTSIDE_MKAD_ORDER,
                        ITEM_ANSWER_OUTSIDE_MKAD_ORDER,
                        TEXT_ANSWER_OUTSIDE_MKAD_ORDER
                });
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

    public void checkAnswerText() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        String result = driver.findElement(answerLocator).getText();
        assertEquals(expectedText, result);
    }

    public void clickOnQuestionButton() {
        driver.findElement(questionLocator).click();
    }

    public void scrollPageDownToFAQ() {
        WebElement tableFAQ = driver.findElement(panelFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
    }
}
