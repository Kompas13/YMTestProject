package ru.bellintegrator.pageFactory.custom.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.bellintegrator.pageFactory.custom.drivers.Manager;

import java.util.function.Consumer;

public class Actions {

    public static org.openqa.selenium.interactions.Actions action;

    public static Consumer<By> hover = (By by) -> {
        action.moveToElement(Manager.getCurrentDriver().findElement(by)).perform();
    };

    public static Consumer<WebElement> hoverElement = (webElement) -> {
        action.moveToElement(webElement);
    };

}
