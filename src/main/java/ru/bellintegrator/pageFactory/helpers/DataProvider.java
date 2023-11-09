package ru.bellintegrator.pageFactory.helpers;

import org.junit.jupiter.params.provider.Arguments;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> provideArguments() {
        List<String> producers = new LinkedList<>(
                List.of("HP", "Lenovo")
        );
        return Stream.of(
                Arguments.of(21000, 30000, producers, 12)
        );
    }
}
