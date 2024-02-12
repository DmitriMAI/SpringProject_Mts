package ru.mts.hw6.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mts.hw6.animal.Animal;
import ru.mts.hw6.repository.AnimalRepository;
import ru.mts.hw6.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Repository
public class AnimalsRepositoryImpl implements AnimalRepository {
    List<Animal> animals;
    CreateAnimalService animalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animalService = createAnimalService;
    }

    @PostConstruct
    private void postConstruct() {
        animals = List.of(animalService.createUniqueAnimals());
    }

    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     * @return Массив из имен животных
     */
    @Override
    public String[] findLeapYearNames() {
        validateAnimals();
        List<String> animalsReturn = new ArrayList<>();

        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                animalsReturn.add(animal.getName());
            }
        }
        return animalsReturn.toArray(new String[0]);
    }

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     * @param n Возраст, выше которого нужно найти
     * @return Массив подходящих животных
     */
    @Override
    public Animal[] findOlderAnimal(int n) {
        validateAnimals();
        List<Animal> animalsReturn = new ArrayList<>();

        for (Animal animal : animals) {
            if (Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > n) {
                animalsReturn.add(animal);
            }
        }
        return animalsReturn.toArray(new Animal[0]);
    }

    /**
     * Ищет в Map дубликаты животных
     */
    @Override
    public Map<Animal, Integer> findDuplicate() {
        validateAnimals();
        Map<Animal, Integer> animalDuplicates = new HashMap<>();
        Set<Animal> uniqueAnimals = new HashSet<>();

        for (Animal animal : animals) {
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
        return animalDuplicates;
    }

    /**
     * Выводит на поток вывода
     * результат работы findDuplicate()
     */
    @Override
    public void printDuplicate() {
        Map<Animal, Integer> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        for (Map.Entry<Animal, Integer> entry : animalDuplicates.entrySet()) {
            System.out.println(entry);
        }
    }

    private void validateAnimals() {
        if (animals == null) {
            throw new IllegalArgumentException("Animals array cannot be null");
        }
        for (Animal animal : animals) {
            if (animal == null) {
                throw new IllegalArgumentException("Some animal is null");
            }
        }
    }

}
