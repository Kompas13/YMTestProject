package bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.bellintegrator.pageFactory.steps.Steps;

import java.util.List;


public class Tests extends BaseTest {
    @Feature("Тестирование Яндекс маркета")
    @DisplayName("Тестирование параметров поиска Яндекс маркета")
    @ParameterizedTest
    @MethodSource("ru.bellintegrator.pageFactory.helpers.DataProvider#provideArguments")
    public void test(int from, int to, List<String> producers, int snippetsNumber) {
        Steps.openBrowserWithYandexMarket();
        Steps.initPageFactoryTestMarket();
        Steps.clickCatalog();
        Steps.hoverMenuComputers();
        Steps.clickLapTops();
        Assertions.assertTrue(Steps.checkPageLaptop(), "Вы находитесь не в разделе -Ноутбуки-");
        Steps.setCosts(from, to);
        Steps.setProducers(producers);
        Assertions.assertTrue(Steps.getSearchingSizeResult()>snippetsNumber,
                "Результаты поиска меньше"+snippetsNumber+"позиций"
                );
        Assertions.assertTrue(Steps.checkAllPages(from, to, producers),
                "несоответствие результатов поиска заданным параметрам"
        );
        Steps.goToFirstPage();
        Steps.saveFirstElementName();
        Steps.sendKeysAndPressButton();
        Assertions.assertTrue(
                Steps.checkResult(),
                "Результаты поиска не содержат навания " +
                        "раннее сохраненного первого элемента из поисковой выдачи"
        );

    }

}
