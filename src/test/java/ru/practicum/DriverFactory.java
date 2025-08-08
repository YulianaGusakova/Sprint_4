package ru.practicum;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static ru.practicum.util.Constants.Base_URL;
import static ru.practicum.util.Constants.IMPLICITY_TIMEOUT;

public class DriverFactory extends ExternalResource {
    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else {
            startChrome();
        }
    }

    private void startChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }

    private void startFirefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
        driver.manage().window().fullscreen();
}
@Override
protected void before() {
    initDriver();
    driver.get(Base_URL);
}
@Override
protected void after() {
    driver.quit();
}
}
