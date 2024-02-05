package ru.mts.hw6.repository;

import ru.mts.hw6.animal.Animal;

import java.util.Map;

public interface AnimalRepository {
    /**
     * При помощи цикла находит всех животных,
     * которые родились в високосный год
     *
     *
     * @return Массив из имен животных
     */
    String[] findLeapYearNames();

    /**
     * При помощи цикла for находит всех
     * животных, возраст которых больше N лет
     *
     *
     * @param N       Возраст, выше которого нужно найти
     * @return Массив подходящих животных
     */
    Animal[] findOlderAnimal(int N);

    /**
     * Ищет дубликаты животных
     *
     *
     */
    Map<Animal, Integer> findDuplicate();

    /**
     * Печатает дубликаты животных
     *
     *
     */
    void printDuplicate();
}
