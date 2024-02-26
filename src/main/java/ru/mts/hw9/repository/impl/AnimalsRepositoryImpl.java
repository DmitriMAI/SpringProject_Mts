package ru.mts.hw9.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mts.hw9.animal.Animal;
import ru.mts.hw9.repository.AnimalRepository;
import ru.mts.hw9.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Repository
public class AnimalsRepositoryImpl implements AnimalRepository {
    private Map<String, List<Animal>> animals;
    private final CreateAnimalService animalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animalService = createAnimalService;
    }

    @PostConstruct
    private void postConstruct() {
        animals = animalService.createUniqueAnimals();
    }

    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     * @return Мапа. Ключ - тип + имя животного, значение - дата
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        validateAnimals();
        Map<String, LocalDate> animalsReturn = new HashMap<>();

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                if (animal.getBirthDate().isLeapYear()) {
                    animalsReturn.put(
                            animal.getBreed() + " " + animal.getName(),
                            animal.getBirthDate());
                }
            }
        }
        return animalsReturn;
    }

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     * @param n Возраст, выше которого нужно найти
     * @return Мап. Ключ - животное, значение - возраст
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        validateAnimals();
        Map<Animal, Integer> animalsReturn = new HashMap<>();
        int oldestYearsOld = 0;
        Animal oldestAnimal = null;

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalList = animals.get(key);
            for (Animal animal : animalList) {
                int animalYearsOld = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();

                if (animalYearsOld > oldestYearsOld) {
                    oldestYearsOld = animalYearsOld;
                    oldestAnimal = animal;
                }
                if (animalYearsOld > n) {
                    animalsReturn.put(animal, animalYearsOld);
                }
            }
        }
        if (animalsReturn.isEmpty()) {
            animalsReturn.put(oldestAnimal, oldestYearsOld);
        }
        return animalsReturn;
    }

    /**
     * Ищет дубликаты животных
     *
     * @return Мап. Ключ - тип животного, значение - количество дубликата
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        validateAnimals();
        Map<Animal, Integer> animalDuplicates = new HashMap<>();
        Set<Animal> uniqueAnimals = new HashSet<>();
        Map<String, Integer> animalsReturn = new HashMap<>();

        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                if (uniqueAnimals.contains(animal)) {
                    if (animalDuplicates.containsKey(animal)) {
                        animalDuplicates.put(animal, animalDuplicates.get(animal) + 1);
                    } else {
                        animalDuplicates.put(animal, 1);
                    }
                } else {
                    uniqueAnimals.add(animal);
                }
            }
        }
        for (Map.Entry<Animal, Integer> entry : animalDuplicates.entrySet()) {
            animalsReturn.put(entry.getKey().getBreed(), entry.getValue());
        }
        return animalsReturn;
    }

    /**
     * Выводит на поток вывода
     * результат работы findDuplicate()
     */
    @Override
    public void printDuplicate() {
        Map<String, Integer> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        for (Map.Entry<String, Integer> entry : animalDuplicates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private void validateAnimals() {
        if (animals == null) {
            throw new IllegalArgumentException("Animals array cannot be null");
        }
        Set<String> keysMap = animals.keySet();
        for (String key : keysMap) {
            List<Animal> animalsList = animals.get(key);
            for (Animal animal : animalsList) {
                if (animal == null) {
                    throw new IllegalArgumentException("Some animal is null");
                }
            }
        }
    }

}
