package ru.mts.hw10.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw10.animal.Animal;
import ru.mts.hw10.animal.impl.Cat;
import ru.mts.hw10.config.MockConfiguration;
import ru.mts.hw10.repository.impl.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
                entry("Fish", 1)
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

    @Test
    @DisplayName("Тест метода findOldAnimalExpensive")
    void findOldAnimalExpensiveTest() {
        List<Animal> animalsList = List.of(
                new Cat("Cat", 10.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat", 5.0, LocalDate.of(2021, 8, 1)),
                new Cat("Cat", 5.0, LocalDate.of(2000, 8, 1))
        );
        List<Animal> expectedAnimals = List.of(
                new Cat("Cat", 10.0, LocalDate.of(2000, 8, 1))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findOldAnimalExpensive(animalsList));
    }

    @Test
    @DisplayName("Тест метода findMinConstAnimals")
    void findMinConstAnimals() {
        List<Animal> animalsList = List.of(
                new Cat("Cat", 10.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat2", 2.0, LocalDate.of(2021, 8, 1)),
                new Cat("Cat", 5.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat1", 1.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat", 5.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat3", 3.0, LocalDate.of(2000, 8, 1))
        );
        List<Animal> expectedAnimals = List.of(
                new Cat("Cat3", 3.0, LocalDate.of(2000, 8, 1)),
                new Cat("Cat2", 2.0, LocalDate.of(2021, 8, 1)),
                new Cat("Cat1", 1.0, LocalDate.of(2000, 8, 1))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findMinConstAnimals(animalsList));
    }
}
