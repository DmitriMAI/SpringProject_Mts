package ru.mts.hw6.service;

import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.factory.AnimalFactory;

import java.util.concurrent.ThreadLocalRandom;

import static ru.mts.hw6.factory.AnimalTypes.*;

/**
 * Интерфейс создания
 * десяти различных животных
 * с помощью цикла while
 */
public interface CreateAnimalService {
    /**
     * Получить животное
     *
     * @return Animal
     */
    Animal getAnimal();

    /**
     * Проинициализировать поле
     */
    void initAnimal();

    /**
     * Дефолтный метод генерации
     * десяти различных животных
     * циклом while
     */
    default Animal[] createUniqueAnimals() {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal[] animals = new Animal[10];
        int i = 0;

        while (i < 10) {
            switch (ThreadLocalRandom.current().nextInt(1, 4)) {
                case 1:
                    animals[i] = animalFactory.getAnimal(WOLF);
                    break;
                case 2:
                    animals[i] = animalFactory.getAnimal(SHARK);
                    break;
                case 3:
                    animals[i] = animalFactory.getAnimal(DOG);
                    break;
                case 4:
                    animals[i] = animalFactory.getAnimal(CAT);
                    break;
                default:
                    animals[i] = animalFactory.getAnimal(CAT);
            }
            i++;
        }
        return animals;
    }
}
