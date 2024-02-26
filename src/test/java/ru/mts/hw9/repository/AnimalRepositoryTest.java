package ru.mts.hw9.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw9.config.MockConfiguration;
import ru.mts.hw9.repository.impl.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

@SpringBootTest
@ActiveProfiles("test")
@Import(MockConfiguration.class)
class AnimalRepositoryTest {
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Test
    @DisplayName("Тест метода findDuplicate")
    void findDuplicateTest() {
        Map<String, Integer> expectedDuplicates = Map.ofEntries(
                entry("Fish", 2)
        );
        Map<String, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Тест метода findDuplicate, но с пустой Map")
    void findDuplicateNotExpectedTest() {
        Map<String, Integer> expectedDuplicates = Map.ofEntries();
        Map<String, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Тест метода findLeapYearNames")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> expectedAnimals = Map.ofEntries(
                entry("Fish S", LocalDate.of(2020, 1, 8)),
                entry("Fish S1", LocalDate.of(2020, 1, 8))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Тест метода findLeapYearNames, но с пустой Map")
    void findLeapYearNamesNotExpectedTest() {
        Map<String, LocalDate> expectedAnimals = new HashMap<>();
        Assertions.assertNotEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }
}
