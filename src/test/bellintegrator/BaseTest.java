package bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.bellintegrator.pageFactory.custom.drivers.Manager;

public class BaseTest {
    /**
     * Браузер
     */
    protected WebDriver webDriver;
    /**
     * Открытие браузера и ожидание прогрузки элементов и скриптов
     */
    @BeforeEach
    public void beforeEachOpenGoogleChrome() {
        Manager.initChrome();
        webDriver = Manager.getCurrentDriver();
    }
    /**
     * Закрытие браузера
     */
    @AfterEach
    public void afterEachQuiteGoogleChrome() {
        webDriver.quit();
        Manager.killCurrentDriver();
    }

}
