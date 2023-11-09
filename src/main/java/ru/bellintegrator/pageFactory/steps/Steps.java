package ru.bellintegrator.pageFactory.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.bellintegrator.pageFactory.custom.utils.Constants;
import ru.bellintegrator.pageFactory.pages.PageFactoryTestYM;
import java.util.List;
import static ru.bellintegrator.pageFactory.custom.drivers.Manager.getCurrentDriver;


public class Steps {

    private static PageFactoryTestYM pageFactoryTestMarket;

    @Step("Открыте браузера со страртовой страницей yandexMarket")
    public static void openBrowserWithYandexMarket() {
        getCurrentDriver().get(Constants.URL);
    }

    @Step("Инициализация PageFactoryTestMarket")
    public static void initPageFactoryTestMarket() {
        pageFactoryTestMarket =
                PageFactory.initElements(getCurrentDriver(), PageFactoryTestYM.class);
    }

    @Step("Клик на кнопке каталог")
    public static void clickCatalog() {
        pageFactoryTestMarket.clickCatalog();
    }

    @Step("Наведение на вкладку меню копьютеры ожидание прогрузки меню")
    public static void hoverMenuComputers() {
        pageFactoryTestMarket.hoverMenuComputers();
    }

    @Step("Клик на вкладке меню LapTops")
    public static void clickLapTops() {
        pageFactoryTestMarket.clickLapTops();
    }

    @Step("Проверка что мы в разделе ноутбуки")
    public static boolean checkPageLaptop() {
        return pageFactoryTestMarket.checkPageLaptop();
    }

    @Step("Установка занчений цены 'от' и 'до'")
    public static void setCosts(int from, int to) {
        pageFactoryTestMarket.setCosts(from, to);
    }

    @Step("Установка фильтра фирм  производителей")
    public static void setProducers(List<String> producers) {
        pageFactoryTestMarket.setProducers(producers);
    }

    @Step("Получение количества показынных элементов из результата поиска")
    public static int getSearchingSizeResult() {
        return pageFactoryTestMarket.getSearchResultNumber();
    }

    @Step("Проверяем все карточки товаров на странице на соответствие производителю и цене.")
    public static boolean checkAllPages(int minPrice, int maxPrice, List<String> stringsProducer) {
        return pageFactoryTestMarket.checkAllPage(minPrice, maxPrice, stringsProducer);
    }

    @Step("Возвращаемся на первую страницу.")
    public static void goToFirstPage() {
        pageFactoryTestMarket.goToFirstPage();
    }

    @Step("Сохранение название первого элемента")
    public static void saveFirstElementName() {
        pageFactoryTestMarket.saveFirstElement();
    }

    @Step("Поиск по названию сохраненного элемента")
    public static void sendKeysAndPressButton() {
        pageFactoryTestMarket.sendKeysAndPressButton();
    }

    @Step("Проверка на содержание результатов поиска на содержания искомого названия")
    public static boolean checkResult() {
        return pageFactoryTestMarket.checkResults();
    }
}
