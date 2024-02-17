package ru.mts.hw6.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.animal.impl.Shark;
import ru.mts.hw6.config.MockConfiguration;
import ru.mts.hw6.repository.impl.AnimalsRepositoryImpl;

import java.time.LocalDate;
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
        Map<Animal, Integer> expectedDuplicates = Map.ofEntries(
                entry(new Shark("S", 0.1, LocalDate.of(2020, 1, 8)), 1)
        );
        Map<Animal, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Тест метода findDuplicate, но с пустой Map")
    void findDuplicateNotExpectedTest() {
        Map<Animal, Integer> expectedDuplicates = Map.ofEntries();
        Map<Animal, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Тест метода findLeapYearNames")
    void findLeapYearNamesTest() {
        String[] expectedAnimalsNames = {"S", "S"};
        Assertions.assertArrayEquals(expectedAnimalsNames, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Тест возврата одного дубликата вместо нескольких")
    void findLeapYearNamesNotExpectedTest() {
        String[] expectedAnimalsNames = {"S"};
        Assertions.assertNotEquals(expectedAnimalsNames, animalsRepository.findLeapYearNames());
    }
}
