package ru.mts.hw9.service.impl;

import org.springframework.stereotype.Service;
import ru.mts.hw9.animal.Animal;
import ru.mts.hw9.factory.AnimalFactory;
import ru.mts.hw9.service.CreateAnimalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static ru.mts.hw9.factory.AnimalTypes.*;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactory animalFactory;

    private Animal animal;

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    /**
     * Получить животное из поля Animal
     *
     * @return Animal
     */
    @Override
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Проинициализировать поле
     */
    @Override
    public void initAnimal() {
        animal = createRandomAnimal();
    }

    @Override
    public AnimalFactory injectForInterface() {
        return animalFactory;
    }


    /**
     * Создает N уникальных животных
     *
     * @param n Сколько животных создать
     * @return Мап. Ключ - тип животного, значнеие массив из этого типа животных
     */
    public Map<String, List<Animal>> createUniqueAnimals(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Wrong n for createUniqueAnimals");
        }
        Map<String, List<Animal>> animals = new HashMap<>();
        Animal generatedAnimal;

        for (int i = 0; i < n; i++) {
            generatedAnimal = createRandomAnimal();
            addToListMap(animals, generatedAnimal.getBreed(), generatedAnimal);
        }
        return animals;
    }

    /**
     * Создает 10 различных животных циклом do while
     *
     * @return Мап. Ключ - тип животного, значнеие массив из этого типа животных
     */
    @Override
    public Map<String, List<Animal>> createUniqueAnimals() {
        Map<String, List<Animal>> animals = new HashMap<>();
        Animal generatedAnimal;
        int i = 0;

        do {
            generatedAnimal = createRandomAnimal();
            addToListMap(animals, generatedAnimal.getBreed(), generatedAnimal);
            i++;
        } while (i < 10);
        return animals;
    }

    /**
     * Создание одного животного
     *
     * @return Animal
     */
    public Animal createRandomAnimal() {
        switch (ThreadLocalRandom.current().nextInt(1, 4)) {
            case 1:
                return animalFactory.getAnimal(WOLF);
            case 2:
                return animalFactory.getAnimal(SHARK);
            case 3:
                return animalFactory.getAnimal(DOG);
            case 4:
                return animalFactory.getAnimal(CAT);
            default:
                throw new IllegalStateException("Wrong ENUM type");
        }
    }
}
