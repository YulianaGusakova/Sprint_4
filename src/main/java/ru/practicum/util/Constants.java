package ru.practicum.util;

public class Constants {
    public static final String Base_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final int IMPLICITY_TIMEOUT = 10;
    public static final int EXPLICITY_TIMEOUT = 10;
    public static final String ONE_DAY = "сутки";
    public static final String TWO_DAYS = "двое суток";
    public static final String THREE_DAYS = "трое суток";
    public static final String FOUR_DAYS = "четверо суток";
    public static final String FIVE_DAYS = "пятеро суток";
    public static final String SIX_DAYS = "шестеро суток";
    public static final String SEVEN_DAYS = "семеро суток";

    public enum ScooterColours {
        BLACK,
        GREY;
    }

    public enum MakeOrderButton {
        TOP_BUTTON,
        DOWN_BUTTON;
    }
}
