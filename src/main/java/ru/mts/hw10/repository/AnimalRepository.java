package ru.mts.hw10.repository;

import ru.mts.hw10.animal.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {
    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     * @return Мапа. Ключ - тип + имя животного, значение - дата
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     * @param n Возраст, выше которого нужно найти
     * @return Мап. Ключ - животное, значение - возраст
     */
    Map<Animal, Integer> findOlderAnimal(int n);

    /**
     * Ищет дубликаты животных
     *
     * @return Мап. Ключ - тип животного, значение - Лист животных
     */
    Map<String, List<Animal>> findDuplicate();

    /**
     * Выводит на поток вывода
     * результат работы findDuplicate()
     */
    void printDuplicate();

    /**
     * Находит средний возраст животных
     *
     * @param animalLists List животных
     */
    void findAverageAge(List<Animal> animalLists);

    /**
     * Находит животных, чей возраст больше 5 лет и цена больше средней
     *
     * @param animalLists Лист животных Animal
     * @return Лист животных Animal
     */
    List<Animal> findOldAnimalExpensive(List<Animal> animalLists);

    /**
     * Найти три животных, с минимальной ценой
     * но расположить их в порядке убывания по имени
     *
     * @param animalLists Лист животных Animal
     * @return Лист животных Animal
     */
    List<Animal> findMinConstAnimals(List<Animal> animalLists);
}
