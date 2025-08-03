package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderConfirmation;
import ru.practicum.pages.OrderFormScooterInfo;
import ru.practicum.pages.OrderFormUserInfo;

import static ru.practicum.util.Constants.*;
import static ru.practicum.util.Constants.MakeOrderButton.DOWN_BUTTON;
import static ru.practicum.util.Constants.MakeOrderButton.TOP_BUTTON;
import static ru.practicum.util.Constants.ScooterColours.BLACK;
import static ru.practicum.util.Constants.ScooterColours.GREY;

@RunWith(Parameterized.class)
public class MakeOrderTest {
    @Rule
    public DriverFactory factory = new DriverFactory();

    private WebDriver driver;
    private MainPage mainPage;
    private OrderFormUserInfo orderFormUserInfo;
    private OrderFormScooterInfo orderFormScooterInfo;
    private final String name;
    private final String surname;
    private final String address;
    private final int stationNumber;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public MakeOrderTest(Enum button, String name, String surname, String address, int stationNumber, String phoneNumber, String date, String rentalPeriod, Enum colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationNumber = stationNumber;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {TOP_BUTTON, "Алексей", "Иванов", "Адрес 1", 55, "79211111111", "31.08.2025", FOUR_DAYS, GREY, "спасибо"},
                {TOP_BUTTON, "Мария", "Петрова", "Адрес 2", 2, "79212222222", "02.09.2025", ONE_DAY, BLACK, "поехали"},
                {DOWN_BUTTON, "Иван", "Соболев", "Адрес 3", 123, "79213333333", "23.08.2025", SIX_DAYS, GREY, "жду"},
                {DOWN_BUTTON, "Степан", "Сидоров", "Адрес 4", 7, "79214444444", "15.08.2025", SEVEN_DAYS, BLACK, "нет"},
        };
    }

    @Test
    public void MakeSuccessfulOrderTest() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver, null, null);
        var orderFormUserInfo = new OrderFormUserInfo(driver);
        var orderFormScooterInfo = new OrderFormScooterInfo(driver);
        var orderConfirmation = new OrderConfirmation(driver);
        mainPage.clickMakeOrderButton(button);
        mainPage.WaitUntilOrderFormAppear();
        orderFormUserInfo.insertName(name);
        orderFormUserInfo.insertSurname(surname);
        orderFormUserInfo.insertAddress(address);
        orderFormUserInfo.chooseMetroStation(stationNumber);
        orderFormUserInfo.insertPhoneNumber(phoneNumber);
        orderFormUserInfo.clickOnNextButton();
        orderFormScooterInfo.waitUntilRentHeaderAppear();
        orderFormScooterInfo.insertDate(date);
        orderFormScooterInfo.insertRentalPeriod(rentalPeriod);
        orderFormScooterInfo.chooseColour(colour);
        orderFormScooterInfo.insertComment(comment);
        orderFormScooterInfo.clickOnOrderButton();
        orderConfirmation.waitUntilConfirmationHeaderAppear();
        orderConfirmation.clickOnButtonYes();
        orderConfirmation.getSuccessfulOrderHeader();
    }
}
