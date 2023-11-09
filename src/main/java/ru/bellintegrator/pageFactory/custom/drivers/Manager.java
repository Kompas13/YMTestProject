package ru.bellintegrator.pageFactory.custom.drivers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.bellintegrator.pageFactory.custom.utils.Actions;
import ru.bellintegrator.pageFactory.properties.TestData;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Manager {
    private static WebDriver currentDriver;
    public static WebDriver getCurrentDriver() {
        return currentDriver;
    }

    public static void initChrome() {
        //System.setProperty("webdriver.chrome.driver","C:\\tmp\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try {
            currentDriver = new ChromeDriver(options);
        } catch (Exception e) {
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер.\n" + e);
            setDriverDefaultSettings();
            initStaticObjects();
        }
    }

    private static void initStaticObjects() {
        Actions.action = new org.openqa.selenium.interactions.Actions(Manager.getCurrentDriver());
    }

    private static void setDriverDefaultSettings() {
        currentDriver.manage().timeouts().implicitlyWait(TestData.propsDriver.defaultTimeout(), TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();
    }

    public static void killCurrentDriver() {
        if (currentDriver != null) {
            currentDriver.quit();
            currentDriver = null;
        }
    }
}
