package ru.bellintegrator.pageFactory.custom.drivers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.bellintegrator.pageFactory.custom.utils.Constants;

public class Waits {
    public static WebDriverWait wait = new WebDriverWait(
            Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT
    );
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementClickable(WebElement element) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT).until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

}
