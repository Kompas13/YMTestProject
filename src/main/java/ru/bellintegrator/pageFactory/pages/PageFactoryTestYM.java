package ru.bellintegrator.pageFactory.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.bellintegrator.pageFactory.custom.drivers.Waits;
import java.util.List;
import java.util.logging.Logger;

public class PageFactoryTestYM {

    /**
     * Логгер
     */
    Logger logger = Logger.getLogger(PageFactoryTestYM.class.getName());

    /**
     * Драйвер браузера
     */
    private final WebDriver webDriver;

    /**
     * Конструктор PageFactory
     *
     * @param webDriver - драйвер браузера
     */
    public PageFactoryTestYM(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 15);
    }

    /**
     * Драйвер ожидания события
     */
    private WebDriverWait webDriverWait;

    /**
     * Элемент - кнопка каталог
     */
    @FindBy(how = How.XPATH, using = "(//*[name()='svg'][@id='hamburger'])[1]")
    private WebElement catalog;

    /**
     * Вкладка меню каталог "Ноутбуки и компьютеры"
     */
    @FindBy(how = How.XPATH, using = "//span[text() = 'Ноутбуки и компьютеры']")
    private WebElement computers;

    /**
     * Вкладка меню компьютеры "Ноутбуки"
     */
    @FindBy(how = How.XPATH, using = "//a[text() = 'Ноутбуки']")
    private WebElement lapTops;

    /**
     * Указатель на раздел "Ноутбуки" на странице
     */
    @FindBy(how = How.XPATH, using = "//span[@aria-label='Значение фильтра Ноутбуки']//span[@itemprop='name'][contains(text(),'Ноутбуки')]")
    private WebElement lapTopsTrueLink;

    /**
     * input для указания цены "ОТ"
     */
    @FindBy(how = How.XPATH, using = "//*[@data-filter-id='glprice']//*[@data-auto='filter-range-min']//input")
    private WebElement costFrom;

    /**
     * input для указания цены "ДО"
     */
    @FindBy(how = How.XPATH, using = "//*[@data-filter-id='glprice']//*[@data-auto='filter-range-max']//input")
    private WebElement costTo;


    /**
     * Не упорядоченный список производителей
     * оборудования с чекбоксами
     */
    @FindBy(how = How.XPATH, using = "//*[text()='Производитель']/../../..//input[@type='checkbox']/..")
    private List<WebElement> producersList;

    /**
     * Список эелементов результата поиска
     */
    @FindBy(how = How.XPATH, using = "//article[@data-autotest-id='product-snippet']")
    private List<WebElement> searchResultList;

    /**
     * Список эелементов результата поиска
     */
    @FindBy(how = How.XPATH, using = "//*[text()='Цена с картой Яндекс Пэй:']/..")
    private List<WebElement> searchPriceList;

    /**
     * Следующая страница в результатах поиска
     */
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Вперёд')]")
    private WebElement linkNextPage;


    /**
     * Первое значение в списке результатов поиска
     */
    private String nameOfFirstElement;

    /**
     * Первая страница результатов поиска
     */
    private String firstPageAfterSearch;

    /**
     * input для ввода искомого товара значения ключа
     */
    @FindBy(how = How.XPATH, using = "//input[@id='header-search']")
    private WebElement inputSearch;


    /**
     * Метод перехода в каталог
     */
    public void clickCatalog() {
        logger.info("Click 'Каталог'");
        catalog.click();
        Waits.waitUntilElementClickable(computers);
    }

    /**
     * Навередение на элемент меню каталога "Ноутбуки и компьютеры"
     */
    public void hoverMenuComputers() {
        logger.info("Hover 'компьютеры'");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(computers).build().perform();
    }

    /**
     * Метод перехода на элемент меню ноутбуки и компьютеры "Ноутбуки"
     */
    public void clickLapTops() {
        logger.info("Переход ноутбуки");
        Actions actions = new Actions(webDriver);
        actions.click(lapTops).build().perform();
    }

    /**
     * Метод проверки действительно ли мы в разделе "Ноутбуки"
     */
    public boolean checkPageLaptop() {
        logger.info("Проверка раздела");
        System.out.println(webDriver.getCurrentUrl().contains("catalog--noutbuki"));
        return webDriver.getCurrentUrl().contains("catalog--noutbuki");
    }

    /**
     * Выставление цен от "From" до "To"
     */
    public void setCosts(int from, int to) {
        logger.info("Установка стоимости от.. до..");
        costFrom.sendKeys(String.valueOf(from));
        costTo.sendKeys(String.valueOf(to));
    }

    /**
     * Отметить выбрано checkBox checked для списка stringsProducer
     */
    public void setProducers(List<String> stringsProducer) {
        logger.info("Выбор производителя");
        Actions actions = new Actions(webDriver);
        stringsProducer.forEach(producer -> {
            for (int i = 0; i < producersList.size(); i++) {
                if (producersList.get(i).getText().contains(producer)) {
                    actions
                            .moveToElement(producersList.get(i))
                            .click(producersList.get(i))
                            .build().perform();
                }
            }
        });
        Waits.sleep(2);
        firstPageAfterSearch = webDriver.getCurrentUrl();
    }

    /**
     * Метод перехода в конец страницы
     */
    public void goEndPage() {
        webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    }

    /**
     * Геттер для количества товара показанного на странице в результатах поиска
     *
     * @return int количество товаров показанных на странице
     */
    public int getSearchResultNumber() {
        logger.info("Возварат количество товаров на странице");
        goEndPage();
        Waits.sleep(1);
        return searchResultList.size();
    }

    /**
     * Метод перехода на сдедующую страницу
     *
     */
    public void goToNextPage() {
        logger.info("Переход на следующую страницу");
        linkNextPage.click();
    }


    /**
     * Метод проверки всех страниц на соответствие цене и производителю
     *
     * @return true - в случае, если цена всех товаров соответствует заданным параметрам
     */
    public boolean checkAllPage(int minPrice, int maxPrice, List<String> elements) {

        while (!webDriver.findElements(By.xpath("//span[contains(text(),'Вперёд')]")).isEmpty()) {
            logger.info("Проверка соответствия  цены товара и производителя на странице " + webDriver.getCurrentUrl());
            Waits.sleep(1);
            goEndPage();
            if(!areAllProductsContainsAnyStringInName(elements)||!areAllProductsHaveCurrentPrice(minPrice, maxPrice)) {
                logger.info("Несоответствие производителя или цены товара на странице " + webDriver.getCurrentUrl());
                return false;
            }
            if (!webDriver.findElements(By.xpath("//span[contains(text(),'Вперёд')]")).isEmpty()) {
                goToNextPage();
            }
        }
        return true;
    }

    /**
     * Метод перехода на первую страницу
     */
    public void goToFirstPage() {
        logger.info("Переход на первую страницу");
        webDriver.get(firstPageAfterSearch);
    }


    /**
     * Метод проверки товаров на соответствие цене.
     * @return true - в случае, если все товары на странице соответствуют фильтру, false - если не соответствуют
     */
    @Step("Проверяем все карточки товаров на странице на соответствие цене (от {minPrice} до {maxPrice}).")
    public boolean areAllProductsHaveCurrentPrice(int minPrice, int maxPrice) {
        logger.info("Проверка соответствия  цены товара заданной цене");
        boolean correctPrice = true;
        List<WebElement> currentList = webDriver.findElements(By.xpath("//article[@data-autotest-id='product-snippet']//*[text()='без:']/.."));
        for (WebElement webElement :currentList) {
            int webElementPrice = Integer.parseInt(webElement.getText()
                    .replaceAll("\\D", ""));
            if (webElementPrice<minPrice||webElementPrice>maxPrice) {
                correctPrice = false;
                logger.info("Не соответствие цены товара " + webElement.getText() + " предельным значениям");
            }
        }
        return correctPrice;
    }

    /**
     * Метод проверки товаров на наличие в названиях товаров наименования производителя.
     * @return true - в случае, если все товары на странице соответствуют фильтру, false - если не соответствуют
     */
    @Step("Проверяем все карточки товаров на странице на соответствие производителю.")
    public boolean areAllProductsContainsAnyStringInName(List<String> elements) {
        logger.info("Проверка соответствия фирмы производителя заданным параметрам");
        Waits.sleep(1);
        List<WebElement> currentList = webDriver.findElements(By.xpath("//article[@data-autotest-id='product-snippet']"));
        boolean correctName;
        correctName = currentList.stream()
                .allMatch(product -> elements.stream()
                        .anyMatch(s -> product.getText().toLowerCase().contains(s.toLowerCase())));
        if (!correctName) {
            logger.info("В наименовании продукта на странице " + webDriver.getCurrentUrl() + " присутствуют продукты несоответствующие фильтру");
        }
        return correctName;
    }

    /**
     * Сохранение первого элемента в переменную nameOfFirstElement
     */
    public void saveFirstElement(){
        logger.info("Сохранение первого элемента в переменную nameOfFirstElement");
        nameOfFirstElement = searchResultList.get(0).findElement(By.xpath(
                "//article[@data-autotest-id='product-snippet']//a/span"
        )).getText();
        logger.info("имя первого элемента" + nameOfFirstElement);
    }

    /**
     * Поиск товара по названию сохраненного элемента
     */
    public void sendKeysAndPressButton() {
        logger.info("Поиск товара по названию сохраненного элемента");
        Actions actions = new Actions(webDriver);
        actions
                .click(inputSearch)
                .sendKeys(nameOfFirstElement)
                .build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * Проверка результатов поиска на содержание названия нужного элемента
     *
     * @return boolean возвращает рузультат проверки в булеан
     */
    public boolean checkResults() {
        logger.info("Проверка результатов поиска на содержание названия нужного элемента");
        Waits.sleep(10);
        return searchResultList.stream().anyMatch(webElement ->
                webElement.getText().contains(nameOfFirstElement)
        );
    }
}
