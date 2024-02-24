package ru.mts.hw6.repository;

import ru.mts.hw6.animal.Animal;

import java.time.LocalDate;
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
     * @return Мап. Ключ - тип животного, значение - количество дубликата
     */
    Map<String, Integer> findDuplicate();

    /**
     * Выводит на поток вывода
     * результат работы findDuplicate()
     */
    void printDuplicate();
}
