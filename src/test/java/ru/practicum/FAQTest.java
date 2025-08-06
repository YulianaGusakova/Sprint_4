package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.practicum.pages.MainPage;

import static org.junit.Assert.assertEquals;

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

    @Parameterized.Parameters(name = "Проверка FAQ: локатор вопроса {0}, локатор ответа {1}, локатор панели ответа {2}, ожидаемый текст ответа {3}")
    public static Object[][] getParameters() {
        return MainPage.getFAQParameters().toArray(new Object[0][]);
    }

    @Test
    public void FAQCheckAnswerTest() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver, question, answer, expectedText);
        mainPage.scrollPageDownToFAQ();
        mainPage.clickOnQuestionButton();
        mainPage.checkAnswerText();
    }
}

