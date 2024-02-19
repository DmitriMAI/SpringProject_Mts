package ru.mts.hw6.service;

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
import ru.mts.hw6.service.impl.CreateAnimalServiceImpl;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@Import(MockConfiguration.class)
class AnimalServiceTest {
    @Autowired
    private CreateAnimalServiceImpl animalService;

    @Test
    @DisplayName("Проверка инициализации сервиса после beanPostProcessor")
    void initAnimalServiceTest() {
        Animal expectedAnimal = new Shark("S", 0.1, LocalDate.of(2020, 1, 8));
        Assertions.assertNotNull(animalService.getAnimal());
        Assertions.assertEquals(expectedAnimal, animalService.getAnimal());
    }

    @Test
    @DisplayName("Проверка создания массива животных при корректном N")
    void createAnimalsTest() {
        Animal[] animals = {
                new Shark("S", 0.1, LocalDate.of(2020, 1, 8)),
                new Shark("S", 0.1, LocalDate.of(2020, 1, 8)),
                new Shark("S", 0.1, LocalDate.of(2020, 1, 8))};
        int animalCount = 3;

        Assertions.assertArrayEquals(animals, animalService.createUniqueAnimals(animalCount));
    }

    @Test
    @DisplayName("Проверка выброса исключения в createUniqueAnimals при отрицательном N")
    void createAnimalsThrowTest() {
        int animalCount = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> animalService.createUniqueAnimals(animalCount));
    }
}
