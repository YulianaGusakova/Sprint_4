package ru.practicum.pages;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    private final By orderForm = By.cssSelector(".Order_Content__bmtHS");

    public static final By QUESTION_0 = By.id("accordion__heading-0");
    public static final By QUESTION_1 = By.id("accordion__heading-1");
    public static final By QUESTION_2 = By.id("accordion__heading-2");
    public static final By QUESTION_3 = By.id("accordion__heading-3");
    public static final By QUESTION_4 = By.id("accordion__heading-4");
    public static final By QUESTION_5 = By.id("accordion__heading-5");
    public static final By QUESTION_6 = By.id("accordion__heading-6");
    public static final By QUESTION_7 = By.id("accordion__heading-7");

    public static final By ANSWER_0 = By.id("accordion__panel-0");
    public static final By ANSWER_1 = By.id("accordion__panel-1");
    public static final By ANSWER_2 = By.id("accordion__panel-2");
    public static final By ANSWER_3 = By.id("accordion__panel-3");
    public static final By ANSWER_4 = By.id("accordion__panel-4");
    public static final By ANSWER_5 = By.id("accordion__panel-5");
    public static final By ANSWER_6 = By.id("accordion__panel-6");
    public static final By ANSWER_7 = By.id("accordion__panel-7");

    public static final By ITEM_ANSWER_0 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-0']");
    public static final By ITEM_ANSWER_1 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-1']");
    public static final By ITEM_ANSWER_2 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-2']");
    public static final By ITEM_ANSWER_3 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-3']");
    public static final By ITEM_ANSWER_4 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-4']");
    public static final By ITEM_ANSWER_5 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-5']");
    public static final By ITEM_ANSWER_6 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-6']");
    public static final By ITEM_ANSWER_7 = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-7']");

    public static final String TEXT_ANSWER_0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String TEXT_ANSWER_1 = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String TEXT_ANSWER_2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
            "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String TEXT_ANSWER_3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String TEXT_ANSWER_4 = "Пока что нет! Но если что-то срочное — " +
            "всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String TEXT_ANSWER_5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
            "даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String TEXT_ANSWER_6 = "Да, пока самокат не привезли. Штрафа не будет, " +
            "объяснительной записки тоже не попросим. Все же свои.";
    public static final String TEXT_ANSWER_7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    public MainPage(WebDriver driver, By questionLocator, By answerLocator) {
        this.driver = driver;
        this.questionLocator = questionLocator;
        this.answerLocator = answerLocator;
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

    public void WaitUntilOrderFormAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }

    public void clickOnGoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(goButton));
        driver.findElement(goButton).click();
    }

    public void CheckErrorImage() {
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

    public void WaitUntilAnswerPanelAppear(By answerPanel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(answerPanel), d -> {
                    String text = driver.findElement(answerPanel).getText();
                    return text != null && !text.isEmpty();
                }));
    }


    public void ClickOnQuestionButton() {
        driver.findElement(questionLocator).click();
    }

    public void ScrollPageDownToFAQ() {
        WebElement tableFAQ = driver.findElement(panelFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
    }
}
