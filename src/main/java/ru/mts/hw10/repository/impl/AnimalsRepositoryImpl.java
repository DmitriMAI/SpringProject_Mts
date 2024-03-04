package ru.mts.hw10.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mts.hw10.animal.Animal;
import ru.mts.hw10.repository.AnimalRepository;
import ru.mts.hw10.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

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
        return animals.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.getBreed() + " " + animal.getName(), Animal::getBirthDate, (r1, r2) -> r1));
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
        Animal oldestAnimal = animals.values().stream()
                .flatMap(List::stream)
                .min(Comparator.comparing(Animal::getBirthDate)).orElseThrow(() -> new IllegalArgumentException("Some animal is null"));
        Map<Animal, Integer> animalsToReturn = animals.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > n)
                .collect(Collectors.toMap(animal -> animal, animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears(), (r1, r2) -> r1));

        return animalsToReturn.isEmpty() ?
                Map.of(oldestAnimal, Period.between(oldestAnimal.getBirthDate(), LocalDate.now()).getYears()) :
                animalsToReturn;
    }

    /**
     * Ищет дубликаты животных
     *
     * @return Мап. Ключ - тип животного, значение - Лист животных
     */
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        validateAnimals();
        List<Animal> allAnimalsList = animals.values().stream().flatMap(List::stream).collect(Collectors.toList());

        return animals.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> Collections.frequency(allAnimalsList, animal) > 1)
                .skip(1)
                .collect(Collectors.groupingBy(Animal::getBreed));
    }

    /**
     * Выводит на поток вывода
     * результат работы findDuplicate()
     */
    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        for (Map.Entry<String, List<Animal>> entry : animalDuplicates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue().size());
        }
    }

    /**
     * Находит средний возраст животных
     *
     * @param animalLists List животных
     */
    public void findAverageAge(List<Animal> animalLists) {
        System.out.println(animalLists.stream()
                .map(Animal::getBirthDate)
                .map(a -> Period.between(a, LocalDate.now()).getYears())
                .mapToInt(Integer::intValue)
                .average().orElse(0)
        );
    }

    /**
     * Находит животных, чей возраст больше 5 лет и цена больше средней
     *
     * @param animalLists Лист животных Animal
     * @return Лист животных Animal
     */
    public List<Animal> findOldAnimalExpensive(List<Animal> animalLists) {
        BigDecimal sumOfCost = animalLists.stream()
                .map(Animal::getCost)
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageCost = sumOfCost.divide(new BigDecimal(animalLists.size()), RoundingMode.UP);
        return animalLists.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().compareTo(averageCost) > 0)
                .sorted((Comparator.comparing(Animal::getBirthDate)))
                .collect(Collectors.toList());
    }

    /**
     * Найти три животных, с минимальной ценой
     * но расположить их в порядке убывания по имени
     *
     * @param animalLists Лист животных Animal
     * @return Лист животных Animal
     */
    public List<Animal> findMinConstAnimals(List<Animal> animalLists) {
        return animalLists.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .collect(Collectors.toList());
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
