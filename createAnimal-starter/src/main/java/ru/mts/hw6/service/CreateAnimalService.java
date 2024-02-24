package ru.mts.hw6.service;

import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * Метод для создания фабрики
     * в default методе в интерфейса
     */
    AnimalFactory injectForInterface();

    /**
     * Дефолтный метод генерации
     * десяти различных животных
     * циклом while
     */
    default Map<String, List<Animal>> createUniqueAnimals() {
        AnimalFactory animalFactory = injectForInterface();
        Map<String, List<Animal>> animals = new HashMap<>();
        Animal animal;
        int i = 0;

        while (i < 10) {
            switch (ThreadLocalRandom.current().nextInt(1, 4)) {
                case 1:
                    animal = animalFactory.getAnimal(WOLF);
                    break;
                case 2:
                    animal = animalFactory.getAnimal(SHARK);
                    break;
                case 3:
                    animal = animalFactory.getAnimal(DOG);
                    break;
                case 4:
                    animal = animalFactory.getAnimal(CAT);
                    break;
                default:
                    animal = animalFactory.getAnimal(UNDEFINED_ANIMAL);
            }
            addToListMap(animals, animal.getBreed(), animal);
            i++;
        }
        return animals;
    }

    /**
     * Дефолтный метод вставки в мап
     *
     * @param map   мап
     * @param key   ключ мап
     * @param value значение мап
     * @param <K>   тип ключа мап
     * @param <V>   тип значения мап
     */
    default <K, V> void addToListMap(Map<K, List<V>> map, K key, V value) {
        List<V> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        list.add(value);
    }
}
