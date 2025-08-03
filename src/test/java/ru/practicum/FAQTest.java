package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.practicum.pages.MainPage;

import static org.junit.Assert.assertEquals;
import static ru.practicum.pages.MainPage.*;

@RunWith(Parameterized.class)
public class FAQTest {
    @Rule
    public DriverFactory factory = new DriverFactory();

    private WebDriver driver;
    private MainPage mainPage;

    private By question;
    private By answer;
    private By answerLocator;
    private String expectedText;


    public FAQTest(By question, By answer, By answerLocator, String expectedText) {
        this.question = question;
        this.answer = answer;
        this.answerLocator = answerLocator;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {QUESTION_0, ANSWER_0, ITEM_ANSWER_0, TEXT_ANSWER_0},
                {QUESTION_1, ANSWER_1, ITEM_ANSWER_1, TEXT_ANSWER_1},
                {QUESTION_2, ANSWER_2, ITEM_ANSWER_2, TEXT_ANSWER_2},
                {QUESTION_3, ANSWER_3, ITEM_ANSWER_3, TEXT_ANSWER_3},
                {QUESTION_4, ANSWER_4, ITEM_ANSWER_4, TEXT_ANSWER_4},
                {QUESTION_5, ANSWER_5, ITEM_ANSWER_5, TEXT_ANSWER_5},
                {QUESTION_6, ANSWER_6, ITEM_ANSWER_6, TEXT_ANSWER_6},
                {QUESTION_7, ANSWER_7, ITEM_ANSWER_7, TEXT_ANSWER_7}
        };
    }

    @Test
    public void FAQCheckAnswerTest() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver, question, answer);
        mainPage.ScrollPageDownToFAQ();
        mainPage.ClickOnQuestionButton();
        mainPage.WaitUntilAnswerPanelAppear(answerLocator);
        String result = driver.findElement(answerLocator).getText();
        assertEquals(expectedText, result);
    }
}

